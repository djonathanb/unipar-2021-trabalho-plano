package br.unipar.plano.domain.procedimento.usecases.impl

import br.unipar.plano.domain.centrais.model.StatusCentral
import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Procedimento
import br.unipar.plano.domain.procedimento.model.ProcedimentoRepository
import br.unipar.plano.domain.procedimento.model.factories.PROCEDIMENTO_CO_ID
import br.unipar.plano.domain.procedimento.services.impl.CancelaProcedimentoUseCaseImpl
import br.unipar.plano.domain.procedimento.services.impl.ProcedimentoNotFoundException
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_PROCEDIMENTO_INEXISTENTE = idProcedimento(false)

class DescredenciaCentralUseCaseImplTest {

    private val procedimentoRepository = mock<ProcedimentoRepository>()
    private val descredenciaProcedimentoUseCase = CancelaProcedimentoUseCaseImpl(procedimentoRepository)

    private val argumentCaptor = argumentCaptor<Procedimento>()

    @BeforeEach
    fun setUp() {
        whenever(procedimentoRepository.findById(eq(PROCEDIMENTO_CO_ID))).thenReturn(Optional.of(procedimento().executa()))
        whenever(procedimentoRepository.findById(eq(ID_PROCEDIMENTO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve cancelar o procedimento informado`() {
        descredenciaProcedimentoUseCase.executa(PROCEDIMENTO_CO_ID)

        verify(procedimentoRepository).save(argumentCaptor.capture())
        val centralSalva = argumentCaptor.firstValue

        assertEquals(StatusCentral.DESCREDENCIADA, centralSalva.status)
    }

    @Test
    fun `deve disparar uma excecao se a central nao existir`() {
        assertThrows<ProcedimentoNotFoundException> {
            descredenciaProcedimentoUseCase.executa(ID_PROCEDIMENTO_INEXISTENTE)
        }

        verify(procedimentoRepository, never()).save(any())
    }

}