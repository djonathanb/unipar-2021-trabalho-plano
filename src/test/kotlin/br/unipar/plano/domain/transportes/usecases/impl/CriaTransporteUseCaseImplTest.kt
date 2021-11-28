package br.unipar.plano.domain.transportes.usecases.impl

import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.centrais.model.Transporte
import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.domain.centrais.usecases.CriaTransporteUseCase
import br.unipar.plano.domain.centrais.usecases.impl.CriaTransporteUseCaseImpl
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import java.util.*

private val ID_TRANSPORTE_INEXISTENTE = IdTransporte(UUID.fromString("7c4ac13b-3d9d-4419-ac92-ef88540669f0"))

class CriaTransporteUseCaseImplTest {

    private val transporteRepository = mock<TransporteRepository>()
    private val criaTransporteUseCase = CriaTransporteUseCaseImpl(transporteRepository)

    private val argumentCaptor = argumentCaptor<Transporte>()

    @BeforeEach
    fun setUp() {
        whenever(transporteRepository.existsById(eq(TRANSPORTE_CO_ID))).thenReturn(true)
        whenever(transporteRepository.existsById(eq(ID_TRANSPORTE_INEXISTENTE))).thenReturn(false)
        `when`(transporteRepository.save(any())).then { it.arguments[0] }
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novoTransporte = criaTransporteUseCase.executa(transporte().with(id = ID_TRANSPORTE_INEXISTENTE))

        verify(transporteRepository).save(argumentCaptor.capture())
        val transporteSalvo = argumentCaptor.firstValue

        assertEquals(novoTransporte, transporteSalvo)
    }

    @Test
    fun `deve disparar uma excecao se uma central com o mesmo id ja existir`() {
        assertThrows<IllegalStateException> {
            criaTransporteUseCase.executa(transporte())
        }

        verify(transporteRepository, never()).save(any())
    }

}