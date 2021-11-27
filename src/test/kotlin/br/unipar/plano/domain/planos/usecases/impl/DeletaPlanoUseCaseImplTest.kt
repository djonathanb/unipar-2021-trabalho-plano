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
import java.util.*

private val ID_PLANO_INEXISTENTE = idPlano(false)

class DeletaPlanoUseCaseImplTest {

    private val planoRepository = mock<PlanoRepository>()
    private val deletaPlanoUseCase = DeletaPlanoUseCaseImpl(planoRepository)

    private val argumentCaptor = argumentCaptor<Plano>()

    @BeforeEach
    fun setUp() {
        whenever(planoRepository.findById(eq(PLANO_CO_ID))).thenReturn(Optional.of(plano()))
        whenever(planoRepository.findById(eq(ID_PLANO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve deletar o plano informado`() {
        deletaPlanoUseCase.executa(PLANO_CO_ID)

        verify(planoRepository).delete(argumentCaptor.capture())
        val planoDeletada = argumentCaptor.firstValue

        assertEquals(PLANO_CO_ID, planoDeletada.id)
    }

    @Test
    fun `deve disparar uma excecao se o plano nao existir`() {
        assertThrows<PlanoNotFoundException> {
            deletaPlanoUseCase.executa(ID_PLANO_INEXISTENTE)
        }

        verify(planoRepository, never()).delete(any())
    }

}