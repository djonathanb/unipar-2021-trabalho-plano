package br.unipar.plano.interfaces.rest.centrais

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.services.CentralApplicationService
import br.unipar.plano.interfaces.dto.CentralDTO
import br.unipar.plano.interfaces.dto.CentralDetailsDTO
import br.unipar.plano.interfaces.dto.CentralSummaryDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/centrais")
class CentralResource(private val centralApplicationService: CentralApplicationService) {

    @Operation(summary = "Cria uma nova central e retorna o endereço do novo recurso")
    @PostMapping
    fun criar(@RequestBody @Valid centralDTO: CentralDTO): ResponseEntity<Void> {
        val idNovaCentral = centralApplicationService.cria(centralDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovaCentral.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @Operation(summary = "Atualiza uma central a partir do seu id e novo estado")
    @PutMapping("/{idCentral}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun atualizar(@PathVariable("idCentral") idCentral: UUID, @RequestBody @Valid centralDTO: CentralDTO) {
        centralApplicationService.atualiza(idCentral = IdCentral(idCentral), centralDTO = centralDTO)
    }

    @Operation(summary = "Deleta uma central a partir do seu id")
    @DeleteMapping("/{idCentral}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun deleta(@PathVariable("idCentral") idCentral: UUID) {
        centralApplicationService.deleta(idCentral = IdCentral(idCentral))
    }

    @PostMapping("/{idCentral}/credenciamento")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun credenciar(@PathVariable("idCentral") idCentral: UUID) {
        centralApplicationService.credenciar(IdCentral(idCentral))
    }

    @DeleteMapping("/{idCentral}/credenciamento")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun descredenciar(@PathVariable("idCentral") idCentral: UUID) {
        centralApplicationService.descredenciar(IdCentral(idCentral))
    }

    @Operation(summary = "Retorna a lista de centrais cadastradas")
    @GetMapping
    fun lista(): ResponseEntity<List<CentralSummaryDTO>> {
        return ResponseEntity.ok(centralApplicationService.lista())
    }

    @Operation(summary = "Busca os detalhes de uma central por id")
    @GetMapping("/{idCentral}")
    fun buscaPorId(@PathVariable("idCentral") idCentral: UUID): ResponseEntity<CentralDetailsDTO> {
        return ResponseEntity.ok(centralApplicationService.buscaPorId(IdCentral(idCentral)))
    }

}
