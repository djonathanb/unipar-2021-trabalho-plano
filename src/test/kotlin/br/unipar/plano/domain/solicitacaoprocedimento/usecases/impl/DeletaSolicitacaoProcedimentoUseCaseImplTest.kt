package br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.SOLICITACAO_PROCEDIMENTO_ID
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.idSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.solicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_SOLICITACAO_INEXISTENTE = idSolicitacaoProcedimento(false)

class DeletaSolicitacaoProcedimentoUseCaseImplTest {

    private val solicitacaoProcedimentoRepository = mock<SolicitacaoProcedimentoRepository>()
    private val deletaSolicitacaoProcedimentoUseCaseImpl = DeletaSolicitacaoProcedimentoUseCaseImpl(solicitacaoProcedimentoRepository)

    private val argumentCaptor = argumentCaptor<SolicitacaoProcedimento>()

    @BeforeEach
    fun setUp() {
        whenever(solicitacaoProcedimentoRepository.findById(eq(SOLICITACAO_PROCEDIMENTO_ID))).thenReturn(Optional.of(solicitacaoProcedimento()))
        whenever(solicitacaoProcedimentoRepository.findById(eq(ID_SOLICITACAO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve deletar a solicitacao informada`() {
        deletaSolicitacaoProcedimentoUseCaseImpl.executa(SOLICITACAO_PROCEDIMENTO_ID)

        verify(solicitacaoProcedimentoRepository).delete(argumentCaptor.capture())
        val solicitacaoProcedimentoDeletada = argumentCaptor.firstValue

        assertEquals(SOLICITACAO_PROCEDIMENTO_ID, solicitacaoProcedimentoDeletada.id)
    }

    @Test
    fun `deve disparar uma excecao se a solicitacao nao existir`() {
        assertThrows<SolicitacaoProcedimentoNotFoundException> {
            deletaSolicitacaoProcedimentoUseCaseImpl.executa(ID_SOLICITACAO_INEXISTENTE)
        }

        verify(solicitacaoProcedimentoRepository, never()).delete(any())
    }

}