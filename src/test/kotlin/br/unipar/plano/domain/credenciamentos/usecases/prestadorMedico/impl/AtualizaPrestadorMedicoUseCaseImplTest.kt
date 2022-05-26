package br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.PRESTADORMEDICO_ID
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.idPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.prestadorMedico
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl.AtualizaPrestMedicoUseCaseImpl
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_PRESTADORMEDICO_INEXISTENTE = idPrestadorMedico(false)

class AtualizaPrestadorMedicoUseCaseImplTest {

    private val prestadorMedicoRepository = mock<PrestadorMedicoRepository>()
    private val atualizaPrestadorMedicoUseCase = AtualizaPrestMedicoUseCaseImpl(prestadorMedicoRepository)

    private val argumentCaptor = argumentCaptor<PrestadorMedico>()

    @BeforeEach
    fun setUp() {
        whenever(prestadorMedicoRepository.findById(eq(PRESTADORMEDICO_ID))).thenReturn(Optional.of(prestadorMedico()))
        whenever(prestadorMedicoRepository.findById(eq(ID_PRESTADORMEDICO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novoNome = "Novo nome"

        atualizaPrestadorMedicoUseCase.executa(PRESTADORMEDICO_ID) {
            it.with(nome = novoNome)
        }

        verify(prestadorMedicoRepository).save(argumentCaptor.capture())
        val prestadorMedicoSalva = argumentCaptor.firstValue

        assertEquals(idPrestadorMedico(), prestadorMedicoSalva.idPrestadorMedico)
        assertEquals(novoNome, prestadorMedicoSalva.nome)
    }

    @Test
    fun `deve garantir que o id permanece o mesmo mesmo que alterado pela funcao de transformacao`() {
        val novoNome = "Novo nome"

        atualizaPrestadorMedicoUseCase.executa(PRESTADORMEDICO_ID) {
            it.with(
                id = idPrestadorMedico(false),
                nome = novoNome
            )
        }

        verify(prestadorMedicoRepository).save(argumentCaptor.capture())
        val prestadorMedicoSalva = argumentCaptor.firstValue

        assertEquals(PRESTADORMEDICO_ID, prestadorMedicoSalva.idPrestadorMedico)
        assertEquals(novoNome, prestadorMedicoSalva.nome)
    }

    @Test
    fun `deve disparar uma excecao se a prestador medico nao existir`() {
        assertThrows<NotFoundException> {
            atualizaPrestadorMedicoUseCase.executa(ID_PRESTADORMEDICO_INEXISTENTE) {
                it.with()
            }
        }

        verify(prestadorMedicoRepository, never()).save(any())
    }

}