package br.unipar.plano.interfaces.rest.planos

import br.unipar.plano.domain.planos.model.IdPlano
import br.unipar.plano.domain.planos.services.PlanoApplicationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/planos")
class PlanoResource(private val planoApplicationService: PlanoApplicationService) {

    @PostMapping
    @ApiResponses(ApiResponse(description = "ok.", responseCode = "201"))
    @Operation(summary = "Cria um novo plano e retorna o endereço do novo recurso")
    fun criar(@RequestBody @Valid planoDTO: PlanoDTO): ResponseEntity<Void> {
        val idNovoPlano = planoApplicationService.cria(planoDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovoPlano.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @Operation(summary = "Deleta um plano a partir do seu id")
    @DeleteMapping("/{idPlano}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun deleta(@PathVariable("idPlano") idPlano: UUID) {
        planoApplicationService.deleta(idPlano = IdPlano(idPlano))
    }

    @GetMapping
    @ApiResponses(ApiResponse(description = "ok.", responseCode = "200"))
    @Operation(summary = "Retorna a lista de planos cadastrados")
    fun lista(): ResponseEntity<List<PlanoSummaryDTO>> {
        return ResponseEntity.ok(planoApplicationService.lista())
    }

    @GetMapping("/{idPlano}")
    @ApiResponses(
            ApiResponse(description = "ok.", responseCode = "200"),
            ApiResponse(description = "Caso não exista o plano.", responseCode = "404")
    )
    @Operation(summary = "Busca os detalhes de um plano por id")
    fun buscaPorId(@PathVariable("idPlano") idPlano: UUID): ResponseEntity<PlanoDetailsDTO> {
        return ResponseEntity.ok(planoApplicationService.buscaPorId(IdPlano(idPlano)))
    }


}
