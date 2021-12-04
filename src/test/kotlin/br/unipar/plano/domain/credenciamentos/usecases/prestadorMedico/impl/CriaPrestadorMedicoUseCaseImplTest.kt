package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.PRESTADORMEDICO_ID
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.idPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.prestadorMedico
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl.CriaPrestMedicoUseCaseImpl
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`

private val ID_PRESTADORMEDICO_INEXISTENTE = idPrestadorMedico(false)

class CriaPrestadorMedicoUseCaseImplTest {

    private val prestadorMedicoRepository = mock<PrestadorMedicoRepository>()
    private val criaPrestadorMedicoUseCase = CriaPrestMedicoUseCaseImpl(prestadorMedicoRepository)

    private val argumentCaptor = argumentCaptor<PrestadorMedico>()

    @BeforeEach
    fun setUp() {
        whenever(prestadorMedicoRepository.existsById(eq(PRESTADORMEDICO_ID))).thenReturn(true)
        whenever(prestadorMedicoRepository.existsById(eq(ID_PRESTADORMEDICO_INEXISTENTE))).thenReturn(false)
        `when`(prestadorMedicoRepository.save(any())).then { it.arguments[0] }
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novaPrestadorMedico = criaPrestadorMedicoUseCase.executa(prestadorMedico().with(id = ID_PRESTADORMEDICO_INEXISTENTE))

        verify(prestadorMedicoRepository).save(argumentCaptor.capture())
        val prestadorMedicoSalva = argumentCaptor.firstValue

        assertEquals(novaPrestadorMedico, prestadorMedicoSalva)
    }

    @Test
    fun `deve disparar uma excecao se uma prestador Medico com o mesmo id ja existir`() {
        assertThrows<IllegalStateException> {
            criaPrestadorMedicoUseCase.executa(prestadorMedico())
        }

        verify(prestadorMedicoRepository, never()).save(any())
    }

}