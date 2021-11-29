package br.unipar.plano.domain.transportes.usecases.impl

import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.model.factories.TRANSPORTE_ID
import br.unipar.plano.domain.transportes.model.StatusTransporte
import br.unipar.plano.domain.transportes.model.Transporte
import br.unipar.plano.domain.transportes.model.factories.transporte
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_TRANSPORTE_INEXISTENTE = IdTransporte(UUID.fromString("7c4ac13b-3d9d-4419-ac92-ef88540669f0"))

class AprovaTransporteUseCaseImplTest {

    private val transporteRepository = mock<TransporteRepository>()
    private val aprovaTransporteUseCase = AprovaTransporteUseCaseImpl(transporteRepository)

    private val argumentCaptor = argumentCaptor<Transporte>()

    @BeforeEach
    fun setUp() {
        whenever(transporteRepository.findById(eq(TRANSPORTE_ID))).thenReturn(Optional.of(transporte()))
        whenever(transporteRepository.findById(eq(ID_TRANSPORTE_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve aprovar o Transporte informado`() {
        aprovaTransporteUseCase.executa(transporte())

        verify(transporteRepository).save(argumentCaptor.capture())
        val transporteSalvo = argumentCaptor.firstValue

        assertEquals(StatusTransporte.APROVADO, transporteSalvo.status)
    }

    @Test
    fun `deve disparar uma excecao se o transporte nao existir`() {
        assertThrows<TransporteNotFoundException> {
            aprovaTransporteUseCase.executa(transporte())
        }
        verify(transporteRepository, never()).save(any())
    }

}