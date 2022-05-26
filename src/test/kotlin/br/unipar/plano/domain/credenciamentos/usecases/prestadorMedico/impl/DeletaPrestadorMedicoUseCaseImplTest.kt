package br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.PRESTADORMEDICO_ID
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.idPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.prestadorMedico
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl.DeletaPrestMedicoUseCaseImpl
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl.PrestMedicoNotFoundException
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_PRESTADORMEDICO_INEXISTENTE = idPrestadorMedico(false)

class DeletaPrestadorMedicoUseCaseImplTest {

    private val prestadorMedicoRepository = mock<PrestadorMedicoRepository>()
    private val deletaPrestadorMedicoUseCase = DeletaPrestMedicoUseCaseImpl(prestadorMedicoRepository)

    private val argumentCaptor = argumentCaptor<PrestadorMedico>()

    @BeforeEach
    fun setUp() {
        whenever(prestadorMedicoRepository.findById(eq(PRESTADORMEDICO_ID))).thenReturn(Optional.of(prestadorMedico()))
        whenever(prestadorMedicoRepository.findById(eq(ID_PRESTADORMEDICO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve deletar o prestador medico informado`() {
        deletaPrestadorMedicoUseCase.executa(PRESTADORMEDICO_ID)

        verify(prestadorMedicoRepository).delete(argumentCaptor.capture())
        val prestadorMedicoDeletado = argumentCaptor.firstValue

        assertEquals(PRESTADORMEDICO_ID, prestadorMedicoDeletado.idPrestadorMedico)
    }

    @Test
    fun `deve disparar uma excecao se a prestador Medico nao existir`() {
        assertThrows<PrestMedicoNotFoundException> {
            deletaPrestadorMedicoUseCase.executa(ID_PRESTADORMEDICO_INEXISTENTE)
        }

        verify(prestadorMedicoRepository, never()).delete(any())
    }

}