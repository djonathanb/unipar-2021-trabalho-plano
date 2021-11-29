package br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.services.prestadorMedico.PrestMedAppService
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

    @GetMapping("/{idPrestadorMedico}")
    fun buscaPorId(@PathVariable("idPrestadorMedico") idPrestadorMedico: UUID): ResponseEntity<PrestadorMedicoDetailsDTO> {
        return ResponseEntity.ok(prestMedAppService.buscaPorId(IdPrestadorMedico(idPrestadorMedico)))
    }

    @PostMapping("/{idPrestadorMedico}/credenciamento")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun credenciar(@PathVariable("idPrestadorMedico") idPrestadorMedico: UUID) {
        prestMedAppService.credenciar(IdPrestadorMedico(idPrestadorMedico))
    }

    @DeleteMapping("/{idPrestadorMedico}/descredenciamento")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun descredenciar(@PathVariable("idPrestadorMedico") idPrestadorMedico: UUID) {
        prestMedAppService.descredenciar(IdPrestadorMedico(idPrestadorMedico))
    }

    @Operation(summary = "Retorna a lista de medicos cadastrados")
    @GetMapping
    fun lista(): ResponseEntity<List<PrestadorMedicoSummaryDTO>> {
        return ResponseEntity.ok(prestMedAppService.lista())
    }
}
