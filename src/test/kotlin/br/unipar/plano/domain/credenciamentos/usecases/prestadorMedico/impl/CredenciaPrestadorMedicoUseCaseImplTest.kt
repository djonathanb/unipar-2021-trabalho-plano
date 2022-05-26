package br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.StatusMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.PRESTADORMEDICO_ID
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.idPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.prestadorMedico
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl.CredenciaPrestMedicoUseCaseImpl
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_PRESTADORMEDICO_INEXISTENTE = idPrestadorMedico(false)

class CredenciaPrestadorMedicoUseCaseImplTest {

    private val prestadorMedicoRepository = mock<PrestadorMedicoRepository>()
    private val credenciaPrestadorMedicoUseCase = CredenciaPrestMedicoUseCaseImpl(prestadorMedicoRepository)

    private val argumentCaptor = argumentCaptor<PrestadorMedico>()

    @BeforeEach
    fun setUp() {
        whenever(prestadorMedicoRepository.findById(eq(PRESTADORMEDICO_ID))).thenReturn(Optional.of(prestadorMedico()))
        whenever(prestadorMedicoRepository.findById(eq(ID_PRESTADORMEDICO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve credenciar a prestador medico informada`() {
        credenciaPrestadorMedicoUseCase.executa(PRESTADORMEDICO_ID)

        verify(prestadorMedicoRepository).save(argumentCaptor.capture())
        val prestadorMedicoSalva = argumentCaptor.firstValue

        assertEquals(StatusMedico.CREDENCIADA, prestadorMedicoSalva.status)
    }

    @Test
    fun `deve disparar uma excecao se a prestador medico nao existir`() {
        assertThrows<NotFoundException> {
            credenciaPrestadorMedicoUseCase.executa(ID_PRESTADORMEDICO_INEXISTENTE)
        }

        verify(prestadorMedicoRepository, never()).save(any())
    }

}