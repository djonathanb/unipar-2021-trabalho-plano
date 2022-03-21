package br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.SOLICITACAO_PROCEDIMENTO_ID
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.idSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.solicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`

private val ID_SOLICITACAO_INEXISTENTE = idSolicitacaoProcedimento(false)

class CriaSolicitacaoProcedimentoUseCaseImplTest {

    private val solicitacaoProcedimentoRepository = mock<SolicitacaoProcedimentoRepository>()
    private val criaSolicitacaoProcedimentoUseCaseImpl = CriaSolicitacaoProcedimentoUseCaseImpl(solicitacaoProcedimentoRepository)

    private val argumentCaptor = argumentCaptor<SolicitacaoProcedimento>()

    @BeforeEach
    fun setUp() {
        whenever(solicitacaoProcedimentoRepository.existsById(eq(SOLICITACAO_PROCEDIMENTO_ID))).thenReturn(true)
        whenever(solicitacaoProcedimentoRepository.existsById(eq(ID_SOLICITACAO_INEXISTENTE))).thenReturn(false)
        `when`(solicitacaoProcedimentoRepository.save(any())).then { it.arguments[0] }
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novaSolicitacaoProcedimento = criaSolicitacaoProcedimentoUseCaseImpl.executa(solicitacaoProcedimento().with(id = ID_SOLICITACAO_INEXISTENTE))

        verify(solicitacaoProcedimentoRepository).save(argumentCaptor.capture())
        val solicitacaoProcedimentoSalva = argumentCaptor.firstValue

        assertEquals(novaSolicitacaoProcedimento, solicitacaoProcedimentoSalva)
    }

    @Test
    fun `deve disparar uma excecao se uma solicitacao com o mesmo id ja existir`() {
        assertThrows<IllegalStateException> {
            criaSolicitacaoProcedimentoUseCaseImpl.executa(solicitacaoProcedimento())
        }
        verify(solicitacaoProcedimentoRepository, never()).save(any())
    }
}