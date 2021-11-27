package br.unipar.plano.interfaces.rest.reembolso

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.services.AgenteApplicationService
import br.unipar.plano.interfaces.dto.ReembolsoDetailsDTO
import br.unipar.plano.interfaces.dto.ReembolsoSummaryDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/reembolso")
class AgenteResource(private val agenteApplicationService: AgenteApplicationService) {

    @PostMapping("/{idReembolso}/autorizacao")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun autorizarReembolso(@PathVariable("idReembolso") idReembolso: UUID) {
        agenteApplicationService.autorizarReembolso(IdReembolso(idReembolso))
    }

    @DeleteMapping("/{idReembolso}/autorizacao")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun rejeitarReembolso(@PathVariable("idReembolso") idReembolso: UUID) {
        agenteApplicationService.rejeitarReembolso(IdReembolso(idReembolso))
    }

    @GetMapping
    fun lista(): ResponseEntity<List<ReembolsoSummaryDTO>> {
        return ResponseEntity.ok(agenteApplicationService.lista())
    }

    @GetMapping("/{idReembolso}")
    fun buscaPorId(@PathVariable("idReembolso") idReembolso: UUID): ResponseEntity<ReembolsoDetailsDTO> {
        return ResponseEntity.ok(agenteApplicationService.buscaPorId(IdReembolso(idReembolso)))
    }
}