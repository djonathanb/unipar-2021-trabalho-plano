package br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoQueryService
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.AtualizaSolicitacaoProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.RejeitaSolicitacaoProcedimentoUseCase
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RejeitaSolicitacaoProcedimentoUseCaseImpl(
    private val solicitacaoProcedimentoQueryService: SolicitacaoProcedimentoQueryService,
    private val atualizaSolicitacaoProcedimentoUseCase: AtualizaSolicitacaoProcedimentoUseCase
) : RejeitaSolicitacaoProcedimentoUseCase {

    override fun executa(idSolicitacaoProcedimento: IdSolicitacaoProcedimento, descricaoRejeicao: String) {
        val solicitacaoProcedimento = solicitacaoProcedimentoQueryService.buscaPorId(idSolicitacaoProcedimento);

        if (solicitacaoProcedimento.statusSolicitacao.equals(StatusSolicitacaoProcedimento.ABERTO)) {
            atualizaSolicitacaoProcedimentoUseCase.executa(idSolicitacaoProcedimento) {
                it.with(
                    statusSolicitacao = StatusSolicitacaoProcedimento.REJEITADO,
                    dataLiberacaoRejeicao = LocalDate.now(),
                    descricaoRejeicao = descricaoRejeicao
                )
            };
        }
    }
}