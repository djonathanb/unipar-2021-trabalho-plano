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

class AtualizaCentralUseCaseImplTest {

    private val centralRepository = mock<CentralRepository>()
    private val atualizaCentralUseCase = AtualizaCentralUseCaseImpl(centralRepository)

    private val argumentCaptor = argumentCaptor<Central>()

    @BeforeEach
    fun setUp() {
        whenever(centralRepository.findById(eq(CENTRAL_CO_ID))).thenReturn(Optional.of(central()))
        whenever(centralRepository.findById(eq(ID_CENTRAL_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novoNome = "Novo nome"

        atualizaCentralUseCase.executa(CENTRAL_CO_ID) {
            it.with(nome = novoNome)
        }

        verify(centralRepository).save(argumentCaptor.capture())
        val centralSalva = argumentCaptor.firstValue

        assertEquals(CENTRAL_CO_ID, centralSalva.id)
        assertEquals(novoNome, centralSalva.nome)
    }

    @Test
    fun `deve garantir que o id permanece o mesmo mesmo que alterado pela funcao de transformacao`() {
        val novoNome = "Novo nome"

        atualizaCentralUseCase.executa(CENTRAL_CO_ID) {
            it.with(
                id = idCentral(false),
                nome = novoNome
            )
        }

        verify(centralRepository).save(argumentCaptor.capture())
        val centralSalva = argumentCaptor.firstValue

        assertEquals(CENTRAL_CO_ID, centralSalva.id)
        assertEquals(novoNome, centralSalva.nome)
    }

    @Test
    fun `deve disparar uma excecao se a central nao existir`() {
        assertThrows<CentralNotFoundException> {
            atualizaCentralUseCase.executa(ID_CENTRAL_INEXISTENTE) {
                it.with()
            }
        }

        verify(centralRepository, never()).save(any())
    }

}