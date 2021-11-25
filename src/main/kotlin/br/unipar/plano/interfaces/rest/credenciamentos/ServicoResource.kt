package br.unipar.plano.interfaces.rest.credenciamentos

import br.unipar.plano.domain.credenciamentos.model.IdServico
import br.unipar.plano.domain.credenciamentos.services.ServicoAppService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/servico")
class ServicoResource (private val servicoAppService: ServicoAppService) {

    @Operation(summary = "Cria um servico")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Servico criado com sucesso")
    ])
    @PostMapping
    fun criar(@RequestBody @Valid servicoDTO: ServicoDTO): ResponseEntity<Void> {
        val idNovoServico  = servicoAppService.cria(servicoDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovoServico.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @GetMapping
    fun lista(): ResponseEntity<List<ServicoSummaryDTO>> {
        return ResponseEntity.ok(servicoAppService.lista())
    }

    @GetMapping("/{idServico}")
    fun buscaPorId(@PathVariable("idServico") idServicoDTO: UUID): ResponseEntity<ServicoDetailsDTO> {
        return ResponseEntity.ok(servicoAppService.buscaPorId(IdServico(idServicoDTO)))
    }

}