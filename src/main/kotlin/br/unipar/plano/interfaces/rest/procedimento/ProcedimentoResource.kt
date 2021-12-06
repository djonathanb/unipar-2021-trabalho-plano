package br.unipar.plano.interfaces.rest.procedimento

import br.unipar.plano.domain.prestador.model.IdPrestador
import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.services.ProcedimentoQueryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/procedimento")
class ProcedimentoResource(val procedimentoQueryService: ProcedimentoQueryService) {

    @PostMapping
    fun insert(@RequestBody @Valid dto: ProcedimentoDTO): ResponseEntity<Void> {
        val idNovoProcedimento = procedimentoQueryService.cria(dto)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idNovoProcedimento.id)
                .toUri()

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/cancelar/{procedimentoId}")
    fun liberarSolicitacao(@PathVariable procedimentoId: IdProcedimento) {
        procedimentoQueryService.cancela(procedimentoId);
    }

    @GetMapping
    fun lista(@RequestBody @Valid idPrestador: IdPrestador,
              @RequestBody @Valid year: Int,
              @RequestBody @Valid month: Int): ResponseEntity<List<ProcedimentoSummaryDTO>> {
        return ResponseEntity.ok(procedimentoQueryService.lista(idPrestador, year, month))
    }
}