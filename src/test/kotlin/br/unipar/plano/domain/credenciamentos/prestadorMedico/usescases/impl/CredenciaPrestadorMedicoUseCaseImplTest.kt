package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_PRESTADORMEDICO_INEXISTENTE = idPrestadorMedico(false)

class CredenciaPrestadorMedicoUseCaseImplTest {

    private val prestadorMedicoRepository = mock<PrestadorMedicoRepository>()
    private val credenciaPrestadorMedicoUseCase = CredenciaPrestadorMedicoUseCaseImplTest(prestadorMedicoRepository)

    private val argumentCaptor = argumentCaptor<PrestadorMedico>()

    @BeforeEach
    fun setUp() {
        whenever(prestadorMedicoRepository.findById(eq(PRESTADORMEDICO_CO_ID))).thenReturn(Optional.of(prestadorMedico()))
        whenever(prestadorMedicoRepository.findById(eq(ID_PRESTADORMEDICO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve credenciar a prestador medico informada`() {
        credenciaPrestadorMedicoUseCase.executa(PRESTADORMEDICO_CO_ID)

        verify(prestadorMedicoRepository).save(argumentCaptor.capture())
        val prestadorMedicoSalva = argumentCaptor.firstValue

        assertEquals(StatusPrestadorMedico.CREDENCIADA, prestadorMedicoSalva.status)
    }

    @Test
    fun `deve disparar uma excecao se a prestador medico nao existir`() {
        assertThrows<PrestadorMedicoNotFoundException> {
            credenciaPrestadorMedicoUseCase.executa(ID_PRESTADORMEDICO_INEXISTENTE)
        }

        verify(prestadorMedicoRepository, never()).save(any())
    }

}