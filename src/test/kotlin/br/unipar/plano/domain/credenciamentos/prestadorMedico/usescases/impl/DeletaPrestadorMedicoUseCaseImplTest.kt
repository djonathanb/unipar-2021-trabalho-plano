package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.model.factories.CENTRAL_CO_ID
import br.unipar.plano.domain.centrais.model.factories.central
import br.unipar.plano.domain.centrais.model.factories.idCentral
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_PRESTADORMEDICO_INEXISTENTE = idPrestadorMedico(false)

class DeletaPrestadorMedicoUseCaseImplTest {

    private val prestadorMedicoRepository = mock<PrestadorMedicoRepository>()
    private val deletaPrestadorMedicoUseCase = DeletaPrestadorMedicoUseCaseImpl(prestadorMedicoRepository)

    private val argumentCaptor = argumentCaptor<PrestadorMedico>()

    @BeforeEach
    fun setUp() {
        whenever(prestadorMedicoRepository.findById(eq(PRESTADORMEDICO_CO_ID))).thenReturn(Optional.of(prestadorMedico()))
        whenever(prestadorMedicoRepository.findById(eq(ID_PRESTADORMEDICO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve deletar o prestador medico informado`() {
        deletaPrestadorMedicoUseCase.executa(PRESTADORMEDICO_CO_ID)

        verify(prestadorMedicoRepository).delete(argumentCaptor.capture())
        val prestadorMedicoDeletado = argumentCaptor.firstValue

        assertEquals(PRESTADORMEDICO_CO_ID, prestadorMedicoDeletado.id)
    }

    @Test
    fun `deve disparar uma excecao se a prestador Medico nao existir`() {
        assertThrows<CentralNotFoundException> {
            deletaPrestadorMedicoUseCase.executa(ID_PRESTADORMEDICO_INEXISTENTE)
        }

        verify(prestadorMedicoRepository, never()).delete(any())
    }

}