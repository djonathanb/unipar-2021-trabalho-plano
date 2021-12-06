package br.unipar.plano.interfaces.rest.solicitacaoprocedimento

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/solicitacao-liberacao-procedimento")
class SolicitacaoProcedimentoResource(val solicitacaoProcedimentoService: SolicitacaoProcedimentoService) {

    @PostMapping
    fun insert(@RequestBody @Valid dto: SolicitacaoProcedimentoDTO): ResponseEntity<Void> {
        val idNovaCentral = solicitacaoProcedimentoService.insert(dto)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovaCentral.id)
            .toUri()

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/liberar/{solicitacaoId}")
    fun liberarSolicitacao(@PathVariable solicitacaoId: IdSolicitacaoProcedimento) {
        solicitacaoProcedimentoService.liberarSolicitacao(solicitacaoId);
    }

    @PutMapping("/rejeitar/{solicitacaoId}")
    fun rejeitarSolicitacao(@PathVariable solicitacaoId: IdSolicitacaoProcedimento, @RequestParam descricaoRejeicao: String) {
        solicitacaoProcedimentoService.rejeitarSolicitacao(solicitacaoId, descricaoRejeicao);
    }
}