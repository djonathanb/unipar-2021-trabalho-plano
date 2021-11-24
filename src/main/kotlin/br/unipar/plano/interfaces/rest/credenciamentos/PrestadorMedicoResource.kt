package br.unipar.plano.interfaces.rest.credenciamentos

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.services.PrestMedAppService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/prestadorMedico")
class PrestadorMedicoResource(private val prestMedAppService: PrestMedAppService) {

    @Operation(summary = "Cria um prestador medico")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Prestador medico criado com sucesso")
    ])
    @PostMapping
    fun criar(@RequestBody @Valid prestadorMedicoDTO: PrestMedDTO): ResponseEntity<Void> {
        val idNovoPrestadorMedico = prestMedAppService.cria(prestadorMedicoDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovoPrestadorMedico.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @GetMapping
    fun lista(): ResponseEntity<List<PrestadorMedicoSummaryDTO>> {
        return ResponseEntity.ok(prestMedAppService.lista())
    }

    @GetMapping("/{idPrestadorMedico}")
    fun buscaPorId(@PathVariable("idPrestadorMedico") idPrestadorMedico: UUID): ResponseEntity<PrestadorMedicoDetailsDTO> {
        return ResponseEntity.ok(prestMedAppService.buscaPorId(IdPrestadorMedico(idPrestadorMedico)))
    }

}
