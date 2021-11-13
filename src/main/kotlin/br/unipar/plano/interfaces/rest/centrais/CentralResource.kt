package br.unipar.plano.interfaces.rest.centrais

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.services.CentralApplicationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/centrais")
class CentralResource(private val centralApplicationService: CentralApplicationService) {

    @Operation(summary = "Cria uma nova central e retorna o endereço do novo recurso")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "central criada com sucesso")
    ])
    @PostMapping
    fun criar(@RequestBody @Valid centralDTO: CentralDTO): ResponseEntity<Void> {
        val idNovaCentral = centralApplicationService.cria(centralDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovaCentral.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @GetMapping
    fun lista(): ResponseEntity<List<CentralSummaryDTO>> {
        return ResponseEntity.ok(centralApplicationService.lista())
    }

    @GetMapping("/{idCentral}")
    fun buscaPorId(@PathVariable("idCentral") idCentral: UUID): ResponseEntity<CentralDetailsDTO> {
        return ResponseEntity.ok(centralApplicationService.buscaPorId(IdCentral(idCentral)))
    }

}
