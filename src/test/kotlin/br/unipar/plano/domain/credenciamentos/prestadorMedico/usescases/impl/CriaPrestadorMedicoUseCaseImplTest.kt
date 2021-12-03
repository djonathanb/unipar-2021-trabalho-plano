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
import org.mockito.Mockito.`when`

private val ID_PRESTADORMEDICO_INEXISTENTE = idPrestadorMedico(false)

class CriaPrestadorMedicoUseCaseImplTest {

    private val prestadorMedicoRepository = mock<PrestadorMedicoRepository>()
    private val criaPrestadorMedicoUseCase = CriaPrestadorMedicoUseCaseImpl(prestadorMedicoRepository)

    private val argumentCaptor = argumentCaptor<Central>()

    @BeforeEach
    fun setUp() {
        whenever(prestadorMedicoRepository.existsById(eq(PRESTADORMEDICO_CO_ID))).thenReturn(true)
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