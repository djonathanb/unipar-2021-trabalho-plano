package br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.SOLICITACAO_PROCEDIMENTO_DESCRICAO_REJEICAO
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.SOLICITACAO_PROCEDIMENTO_ID
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.solicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoQueryService
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.AtualizaSolicitacaoProcedimentoUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RejeitaSolicitacaoProcedimentoUseCaseImplTest {

    private val solicitacaoProcedimentoQueryService = mock<SolicitacaoProcedimentoQueryService>()
    private val atualizaSolicitacaoProcedimentoUseCase = mock<AtualizaSolicitacaoProcedimentoUseCase>()
    private val rejeitaSolicitacaoProcedimentoUseCaseImpl = RejeitaSolicitacaoProcedimentoUseCaseImpl(
        solicitacaoProcedimentoQueryService,
        atualizaSolicitacaoProcedimentoUseCase
    )

    //dificultades com a implantacao
    fun `deve rejeitar a solicitacao informada`() {
        whenever(solicitacaoProcedimentoQueryService.buscaPorId(SOLICITACAO_PROCEDIMENTO_ID)).thenReturn(solicitacaoProcedimento())
        rejeitaSolicitacaoProcedimentoUseCaseImpl.executa(SOLICITACAO_PROCEDIMENTO_ID, SOLICITACAO_PROCEDIMENTO_DESCRICAO_REJEICAO)
        verify(atualizaSolicitacaoProcedimentoUseCase).executa(SOLICITACAO_PROCEDIMENTO_ID){
            it.with(
                statusSolicitacao = StatusSolicitacaoProcedimento.REJEITADO,
                dataLiberacaoRejeicao = LocalDate.now(),
                descricaoRejeicao = SOLICITACAO_PROCEDIMENTO_DESCRICAO_REJEICAO
            )
        }
    }

    @Test
    fun `deve disparar uma excecao se a solicitacao nao existir`() {
        whenever(solicitacaoProcedimentoQueryService.buscaPorId(SOLICITACAO_PROCEDIMENTO_ID)).thenReturn(solicitacaoProcedimento(
            statusSolicitacao = StatusSolicitacaoProcedimento.REJEITADO
        ))
        rejeitaSolicitacaoProcedimentoUseCaseImpl.executa(SOLICITACAO_PROCEDIMENTO_ID, SOLICITACAO_PROCEDIMENTO_DESCRICAO_REJEICAO)
        verify(atualizaSolicitacaoProcedimentoUseCase, never()).executa(SOLICITACAO_PROCEDIMENTO_ID, {solicitacaoProcedimento()})
    }
}