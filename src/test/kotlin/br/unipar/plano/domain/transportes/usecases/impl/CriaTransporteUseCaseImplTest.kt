package br.unipar.plano.domain.transportes.usecases.impl

import br.unipar.plano.domain.transportes.model.Transporte
import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.model.factories.TRANSPORTE_ID
import br.unipar.plano.domain.transportes.model.factories.transporte
import br.unipar.plano.domain.transportes.model.factories.idTransporte
import br.unipar.plano.domain.centrais.usecases.impl.CriaTransporteUseCaseImpl
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`

private val ID_TRANSPORTE_INEXISTENTE = idTransporte(false)

class CriaTransporteUseCaseImplTest {

    private val transporteRepository = mock<TransporteRepository>()
    private val criaTransporteUseCase = CriaTransporteUseCaseImpl(transporteRepository)

    private val argumentCaptor = argumentCaptor<Transporte>()

    @BeforeEach
    fun setUp() {
        whenever(transporteRepository.existsById(eq(TRANSPORTE_ID))).thenReturn(true)
        whenever(transporteRepository.existsById(eq(ID_TRANSPORTE_INEXISTENTE))).thenReturn(false)
        `when`(transporteRepository.save(any())).then { it.arguments[0] }
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novaTransporte = criaTransporteUseCase.executa(transporte().with(id = ID_TRANSPORTE_INEXISTENTE))

        verify(transporteRepository).save(argumentCaptor.capture())
        val transporteSalvo = argumentCaptor.firstValue

        assertEquals(novaTransporte, transporteSalvo)
    }

    @Test
    fun `deve disparar uma excecao se um transporte com o mesmo id ja existir`() {
        assertThrows<IllegalStateException> {
            criaTransporteUseCase.executa(transporte())
        }
        verify(transporteRepository, never()).save(any())
    }

}