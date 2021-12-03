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

private val ID_PRESTADORMEDICO_INEXISTENTE = idPrestadorMedico(false)

class DescredenciaPrestadorMedicoUseCaseImplTest {

    private val prestadorMedicoRepository = mock<PrestadorMedicoRepository>()
    private val descredenciaPrestadorMedicoUseCase = DescredenciaCentralUseCaseImpl(prestadorMedicoRepository)

    private val argumentCaptor = argumentCaptor<Central>()

    @BeforeEach
    fun setUp() {
        whenever(prestadorMedicoRepository.findById(eq(PRESTADORMEDICO_CO_ID))).thenReturn(Optional.of(prestadorMedico().credencia()))
        whenever(prestadorMedicoRepository.findById(eq(ID_PRESTADORMEDICO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve descredenciar o prestador medico informado`() {
        descredenciaPrestadorMedicoUseCase.executa(PRESTADORMEDICO_CO_ID)

        verify(prestadorMedicoRepository).save(argumentCaptor.capture())
        val prestadorMedicoSalvo = argumentCaptor.firstValue

        assertEquals(StatusPrestadorMedico.DESCREDENCIADO, prestadorMedicoSalvo.status)
    }

    @Test
    fun `deve disparar uma excecao se a central nao existir`() {
        assertThrows<CentralNotFoundException> {
            descredenciaPrestadorMedicoUseCase.executa(ID_PRESTADORMEDICO_INEXISTENTE)
        }

        verify(prestadorMedicoRepository, never()).save(any())
    }

}