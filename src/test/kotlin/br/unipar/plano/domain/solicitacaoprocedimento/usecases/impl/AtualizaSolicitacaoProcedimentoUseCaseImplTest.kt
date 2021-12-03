package br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
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

class AtualizaSolicitacaoProcedimentoUseCaseImplTest {

    private val solicitacaoProcedimentoRespository = mock<SolicitacaoProcedimentoRepository>()
    private val atualizaSolicitacaoProcedimentoUseCase = AtualizaSolicitacaoProcedimentoUseCaseImpl(solicitacaoProcedimentoRespository);

    private val argumentCaptor = argumentCaptor<SolicitacaoProcedimento>()

    @BeforeEach
    fun setUp() {
        whenever(solicitacaoProcedimentoRespository.findById(eq(SOLICITACAO_PROCEDIMENTO_ID))).thenReturn(Optional.of(solicitacaoProcedimento()))
        whenever(solicitacaoProcedimentoRespository.findById(eq(ID_SOLICITACAO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val statusSolicitacaoNovo = StatusSolicitacaoProcedimento.LIBERADO

        atualizaSolicitacaoProcedimentoUseCase.executa(SOLICITACAO_PROCEDIMENTO_ID) {
            it.with(statusSolicitacao = statusSolicitacaoNovo)
        }

        verify(solicitacaoProcedimentoRespository).save(argumentCaptor.capture())
        val solicitacaoSalva = argumentCaptor.firstValue

        assertEquals(SOLICITACAO_PROCEDIMENTO_ID, solicitacaoSalva.id)
        assertEquals(statusSolicitacaoNovo, solicitacaoSalva.statusSolicitacao)
    }

    @Test
    fun `deve garantir que o id permanece o mesmo mesmo que alterado pela funcao de transformacao`() {
        val statusSolicitacaoNovo = StatusSolicitacaoProcedimento.LIBERADO

        atualizaSolicitacaoProcedimentoUseCase.executa(SOLICITACAO_PROCEDIMENTO_ID) {
            it.with(
                id = idSolicitacaoProcedimento(false),
                statusSolicitacao = statusSolicitacaoNovo
            )
        }

        verify(solicitacaoProcedimentoRespository).save(argumentCaptor.capture())
        val solicitacaoSalva = argumentCaptor.firstValue

        assertEquals(SOLICITACAO_PROCEDIMENTO_ID, solicitacaoSalva.id)
        assertEquals(statusSolicitacaoNovo, solicitacaoSalva.statusSolicitacao)
    }

    @Test
    fun `deve disparar uma excecao se a solicitacao nao existir`() {
        assertThrows<SolicitacaoProcedimentoNotFoundException> {
            atualizaSolicitacaoProcedimentoUseCase.executa(ID_SOLICITACAO_INEXISTENTE) {
                it.with()
            }
        }

        verify(solicitacaoProcedimentoRespository, never()).save(any())
    }
}