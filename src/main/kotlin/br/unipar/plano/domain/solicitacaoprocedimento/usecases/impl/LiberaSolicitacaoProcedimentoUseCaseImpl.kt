package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.usecases.LiberaSolicitacaoProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoQueryService
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.AtualizaSolicitacaoProcedimentoUseCase
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LiberaSolicitacaoProcedimentoUseCaseImpl(
    private val solicitacaoProcedimentoQueryService: SolicitacaoProcedimentoQueryService,
    private val atualizaSolicitacaoProcedimentoUseCase: AtualizaSolicitacaoProcedimentoUseCase
) : LiberaSolicitacaoProcedimentoUseCase {

    override fun executa(idSolicitacaoProcedimento: IdSolicitacaoProcedimento) {
        //validar carteirinha //TODO
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
                        dataLiberacaoRejeicao = LocalDate.now()
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
            solicitacoesProcecimentos.filter { solicitacao -> solicitacao.dataLiberacaoRejeicao.dayOfMonth == solicitacaoProcedimento.dataLiberacaoRejeicao.dayOfMonth };

        return solicitacaoProcedimentoMesmoMes.size > 1;
    }
}