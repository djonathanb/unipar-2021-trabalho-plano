package br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.SOLICITACAO_PROCEDIMENTO_ID
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.solicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoQueryService
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.AtualizaSolicitacaoProcedimentoUseCase
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class LiberaSolicitacaoProcedimentoUseCaseImplTest {

    private val solicitacaoProcedimentoRespository = mock<SolicitacaoProcedimentoRepository>()
    private val solicitacaoProcedimentoQueryService = mock<SolicitacaoProcedimentoQueryService>()
    private val atualizaSolicitacaoProcedimentoUseCase = mock<AtualizaSolicitacaoProcedimentoUseCase>()
    private val liberaSolicitacaoProcedimentoUseCaseImpl = LiberaSolicitacaoProcedimentoUseCaseImpl(
        solicitacaoProcedimentoQueryService,
        atualizaSolicitacaoProcedimentoUseCase
    )
    private val argumentCaptor = argumentCaptor<SolicitacaoProcedimento>()

    //dificultades com a implantacao
    fun `deve liberar a solicitacao`() {
        whenever(solicitacaoProcedimentoQueryService.buscaPorId(SOLICITACAO_PROCEDIMENTO_ID)).thenReturn(
            solicitacaoProcedimento()
        )
        liberaSolicitacaoProcedimentoUseCaseImpl.executa(SOLICITACAO_PROCEDIMENTO_ID);
        verify(atualizaSolicitacaoProcedimentoUseCase).executa(SOLICITACAO_PROCEDIMENTO_ID){
            it.with(
                statusSolicitacao = StatusSolicitacaoProcedimento.LIBERADO,
                dataLiberacaoRejeicao = LocalDate.now(),
            )
        }
    }

    @Test
    fun `nao deve liberar a solicitacao`() {
        whenever(solicitacaoProcedimentoQueryService.buscaPorId(SOLICITACAO_PROCEDIMENTO_ID)).thenReturn(
            solicitacaoProcedimento(statusSolicitacao = StatusSolicitacaoProcedimento.LIBERADO)
        )
        liberaSolicitacaoProcedimentoUseCaseImpl.executa(SOLICITACAO_PROCEDIMENTO_ID);
        verify(atualizaSolicitacaoProcedimentoUseCase, never()).executa(SOLICITACAO_PROCEDIMENTO_ID, {solicitacaoProcedimento()})
    }
}