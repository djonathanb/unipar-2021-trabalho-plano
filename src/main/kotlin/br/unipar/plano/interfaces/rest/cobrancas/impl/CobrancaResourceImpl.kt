package br.unipar.plano.interfaces.rest.cobrancas.impl

import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.service.CobrancaService
import br.unipar.plano.interfaces.rest.cobrancas.CobrancaDetailsDTO
import br.unipar.plano.interfaces.rest.cobrancas.CobrancaResource
import br.unipar.plano.interfaces.rest.cobrancas.CobrancaSummaryDTO
import br.unipar.plano.interfaces.rest.cobrancas.RegistrarCobrancaDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/cobrancas")
class CobrancaResourceImpl(private val service: CobrancaService) : CobrancaResource {
    @GetMapping
    @ApiResponses(ApiResponse(description = "ok.", responseCode = "200"))
    @Operation(description = "Retorna todas as cobranças já registradas.")
    override fun buscaTodos(): ResponseEntity<List<CobrancaSummaryDTO>> =
        ResponseEntity.ok(service.buscaTodos())


    @GetMapping("/{id}")
    @ApiResponses(
        ApiResponse(description = "ok.", responseCode = "200"),
        ApiResponse(description = "Caso não exista a cobrança.", responseCode = "404")
    )
    @Operation(description = "Retorna a cobrança a partir do ID informado.")
    override fun buscaPorId(@PathVariable("id") id: UUID): ResponseEntity<CobrancaDetailsDTO> =
        ResponseEntity.ok(service.buscarPorId(IdCobranca(id)))

    @PostMapping
    @ApiResponses(
        ApiResponse(description = "ok.", responseCode = "201"),
        ApiResponse(
            description = "Caso já exista uma cobrança não cancelada no mês e ano informado.\n Caso seja requisitado um registro de cobrança com data futura.",
            responseCode = "400"
        )
    )
    @Operation(description = "Registra uma cobrança a partir do contrato informado para a data informada.")
    override fun registrarCobranca(@Valid @RequestBody dto: RegistrarCobrancaDTO): ResponseEntity<Void> {
        val cobranca = service.registrarCobranca(dto.contrato.toModel(), dto.dataEmissao)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(cobranca.id.id).toUri()
        return ResponseEntity.created(uri).build()
    }

    @PutMapping("/{id}/cancelamento")
    @ApiResponses(
        ApiResponse(description = "ok.", responseCode = "200"),
        ApiResponse(
            description = "Pode retornar erro caso já exista uma cobrança não cancelada no mês e ano informado.",
            responseCode = "400"
        ),
        ApiResponse(description = "Caso não exista a cobrança.", responseCode = "404")
    )
    @Operation(description = "Cancela a cobrança informada.")
    override fun cancelarCobranca(@PathVariable("id") id: UUID): ResponseEntity<CobrancaDetailsDTO> =
        ResponseEntity.ok(service.cancelarCobranca(IdCobranca(id)))
}