package br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoQueryService
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.AtualizaSolicitacaoProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.LiberaSolicitacaoProcedimentoUseCase
import org.springframework.stereotype.Service

@Service
class LiberaSolicitacaoProcedimentoUseCaseImpl(
    private val solicitacaoProcedimentoQueryService: SolicitacaoProcedimentoQueryService,
    private val atualizaSolicitacaoProcedimentoUseCase: AtualizaSolicitacaoProcedimentoUseCase
) : LiberaSolicitacaoProcedimentoUseCase {

    override fun executa(idSolicitacaoProcedimento: IdSolicitacaoProcedimento) {
        val solicitacaoProcedimento = solicitacaoProcedimentoQueryService.buscaPorId(idSolicitacaoProcedimento);

        if (!solicitacaoProcedimento.statusSolicitacao.equals(StatusSolicitacaoProcedimento.LIBERADO)) {
            val solicitacoes = solicitacaoProcedimentoQueryService.findByProcedimentoAndStatusSolicitacaoEquals(
                solicitacaoProcedimento.procedimento,
                StatusSolicitacaoProcedimento.LIBERADO
            );

            if (!existeDuasSolitacoesLiberadasNoMesmoMes(solicitacoes, solicitacaoProcedimento)) {
                atualizaSolicitacaoProcedimentoUseCase.executa(idSolicitacaoProcedimento) {
                    it.with(
                        statusSolicitacao = StatusSolicitacaoProcedimento.LIBERADO,
                    )
                };
            }
        }
    }

    private fun existeDuasSolitacoesLiberadasNoMesmoMes(
        solicitacoesProcecimentos: List<SolicitacaoProcedimento>,
        solicitacaoProcedimento: SolicitacaoProcedimento
    ): Boolean {

        val solicitacaoProcedimentoMesmoMes =
            solicitacoesProcecimentos.filter { solicitacao -> solicitacao.dataLiberacaoRejeicao?.dayOfMonth == solicitacaoProcedimento.dataLiberacaoRejeicao?.dayOfMonth };

        return solicitacaoProcedimentoMesmoMes.size > 1;
    }
}