package br.unipar.plano.interfaces.rest.reembolso

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.services.AgenteApplicationService
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoDetailsDTO
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoRejeitadoDTO
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoSummaryDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import org.springframework.http.ResponseEntity
import javax.validation.Valid

@RestController
@RequestMapping("/reembolso")
class AgenteResource(private val agenteApplicationService: AgenteApplicationService) {

    @Operation(summary = "Autoriza um reembolso")
    @PostMapping("/{idReembolso}/autorizacao")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun autorizarReembolso(@PathVariable("idReembolso") idReembolso: UUID) {
        agenteApplicationService.autorizarReembolso(IdReembolso(idReembolso))
    }

    @Operation(summary = "Rejeita um reembolso")
    @PutMapping("/{idReembolso}/autorizacao")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun rejeitarReembolso(@PathVariable("idReembolso") idReembolso: UUID, @RequestBody @Valid reembolsoRejeitadoDTO : ReembolsoRejeitadoDTO) {
        agenteApplicationService.rejeitarReembolso(idReembolso = IdReembolso(idReembolso), reembolsoRejeitadoDTO = reembolsoRejeitadoDTO)
    }

    @Operation(summary = "Retorna a lista de reembolsos cadastrados")
    @GetMapping
    fun lista(): ResponseEntity<List<ReembolsoSummaryDTO>> {
        return ResponseEntity.ok(agenteApplicationService.lista())
    }

    @Operation(summary = "Busca os detalhes de um reembolso por id")
    @GetMapping("/{idReembolso}")
    fun buscaPorId(@PathVariable("idReembolso") idReembolso: UUID): ResponseEntity<ReembolsoDetailsDTO> {
        return ResponseEntity.ok(agenteApplicationService.buscaPorId(IdReembolso(idReembolso)))
    }
}