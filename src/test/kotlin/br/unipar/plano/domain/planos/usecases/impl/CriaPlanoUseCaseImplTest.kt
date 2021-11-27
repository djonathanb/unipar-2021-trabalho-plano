package br.unipar.plano.domain.planos.usecases.impl

import br.unipar.plano.domain.planos.model.Plano
import br.unipar.plano.domain.planos.model.PlanoRepository
import br.unipar.plano.domain.planos.model.factories.PLANO_CO_ID
import br.unipar.plano.domain.planos.model.factories.plano
import br.unipar.plano.domain.planos.model.factories.idPlano
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`

private val ID_PLANO_INEXISTENTE = idPlano(false)

class CriaPlanoUseCaseImplTest {

    private val planoRepository = mock<PlanoRepository>()
    private val criaPlanoUseCase = CriaPlanoUseCaseImpl(planoRepository)

    private val argumentCaptor = argumentCaptor<Plano>()

    @BeforeEach
    fun setUp() {
        whenever(planoRepository.existsById(eq(PLANO_CO_ID))).thenReturn(true)
        whenever(planoRepository.existsById(eq(ID_PLANO_INEXISTENTE))).thenReturn(false)
        `when`(planoRepository.save(any())).then { it.arguments[0] }
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novoPlano = criaPlanoUseCase.executa(plano().with(id = ID_PLANO_INEXISTENTE))

        verify(planoRepository).save(argumentCaptor.capture())
        val planoSalvo = argumentCaptor.firstValue

        assertEquals(novoPlano, planoSalvo)
    }

    @Test
    fun `deve disparar uma excecao se um plano com o mesmo id ja existir`() {
        assertThrows<IllegalStateException> {
            criaPlanoUseCase.executa(plano())
        }

        verify(planoRepository, never()).save(any())
    }

}