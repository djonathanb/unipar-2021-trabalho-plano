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

private val ID_CENTRAL_INEXISTENTE = idCentral(false)

class DeletaCentralUseCaseImplTest {

    private val centralRepository = mock<CentralRepository>()
    private val deletaCentralUseCase = DeletaCentralUseCaseImpl(centralRepository)

    private val argumentCaptor = argumentCaptor<Central>()

    @BeforeEach
    fun setUp() {
        whenever(centralRepository.findById(eq(CENTRAL_CO_ID))).thenReturn(Optional.of(central()))
        whenever(centralRepository.findById(eq(ID_CENTRAL_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve deletar a central informada`() {
        deletaCentralUseCase.executa(CENTRAL_CO_ID)

        verify(centralRepository).delete(argumentCaptor.capture())
        val centralDeletada = argumentCaptor.firstValue

        assertEquals(CENTRAL_CO_ID, centralDeletada.id)
    }

    @Test
    fun `deve disparar uma excecao se a central nao existir`() {
        assertThrows<CentralNotFoundException> {
            deletaCentralUseCase.executa(ID_CENTRAL_INEXISTENTE)
        }

        verify(centralRepository, never()).delete(any())
    }

}