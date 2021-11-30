package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.usecases.RejeitaSolicitacaoProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoQueryService
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.AtualizaSolicitacaoProcedimentoUseCase
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RejeitaSolicitacaoUseCaseImpl(
    private val solicitacaoProcedimentoQueryService: SolicitacaoProcedimentoQueryService,
    private val atualizaSolicitacaoProcedimentoUseCase: AtualizaSolicitacaoProcedimentoUseCase
) : RejeitaSolicitacaoProcedimentoUseCase {

    override fun executa(solicitacaoID: IdSolicitacaoProcedimento, descricaoRejeicao: String) {
        val solicitacaoProcedimento = solicitacaoProcedimentoQueryService.buscaPorId(solicitacaoID);

        if (solicitacaoProcedimento.statusSolicitacao.equals(StatusSolicitacaoProcedimento.ABERTO)) {
            atualizaSolicitacaoProcedimentoUseCase.executa(solicitacaoID) {
                it.with(
                    statusSolicitacao = StatusSolicitacaoProcedimento.LIBERADO,
                    descricaoRejeicao = descricaoRejeicao,
                    dataLiberacaoRejeicao = LocalDate.now()
                )
            };
        }
    }
}