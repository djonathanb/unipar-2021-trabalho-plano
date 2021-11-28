package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.model.StatusCentral
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

class AprovaTransporteUseCaseImplTest {

    private val centralRepository = mock<CentralRepository>()
    private val credenciaCentralUseCase = CredenciaCentralUseCaseImpl(centralRepository)

    private val argumentCaptor = argumentCaptor<Central>()

    @BeforeEach
    fun setUp() {
        whenever(centralRepository.findById(eq(CENTRAL_CO_ID))).thenReturn(Optional.of(central()))
        whenever(centralRepository.findById(eq(ID_CENTRAL_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve credenciar a central informada`() {
        credenciaCentralUseCase.executa(CENTRAL_CO_ID)

        verify(centralRepository).save(argumentCaptor.capture())
        val centralSalva = argumentCaptor.firstValue

        assertEquals(StatusCentral.CREDENCIADA, centralSalva.status)
    }

    @Test
    fun `deve disparar uma excecao se a central nao existir`() {
        assertThrows<CentralNotFoundException> {
            credenciaCentralUseCase.executa(ID_CENTRAL_INEXISTENTE)
        }

        verify(centralRepository, never()).save(any())
    }

}