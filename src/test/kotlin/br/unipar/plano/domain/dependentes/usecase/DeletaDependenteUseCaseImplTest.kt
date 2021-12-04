package br.unipar.plano.domain.dependentes.usecase

import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.dependentes.model.DependenteRepository
import br.unipar.plano.domain.dependentes.model.factories.DEPENDENTE_CO_ID
import br.unipar.plano.domain.dependentes.model.factories.dependente
import br.unipar.plano.domain.dependentes.model.factories.idDependente
import br.unipar.plano.domain.dependentes.usecase.impl.DeletaDependenteUseCaseImpl
import br.unipar.plano.domain.dependentes.usecase.impl.DependenteNotFoundException
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_DEPENDENTE_INEXISTENTE = idDependente(false)

class DeletaDependenteUseCaseImplTest {

    private val dependenteRepository = mock<DependenteRepository>()
    private val deletaDependenteUseCase = DeletaDependenteUseCaseImpl(dependenteRepository)

    private val argumentCaptor = argumentCaptor<Dependente>()

    @BeforeEach
    fun setUp() {
        whenever(dependenteRepository.findById(eq(DEPENDENTE_CO_ID))).thenReturn(Optional.of(dependente()))
        whenever(dependenteRepository.findById(eq(ID_DEPENDENTE_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve deletar a central informada`() {
        deletaDependenteUseCase.executa(DEPENDENTE_CO_ID)

        verify(dependenteRepository).delete(argumentCaptor.capture())
        val dependenteDeletado = argumentCaptor.firstValue

        Assertions.assertEquals(DEPENDENTE_CO_ID, dependenteDeletado.idDependente)
    }

    @Test
    fun `deve disparar uma excecao se a pessoa nao existir`() {
        assertThrows<DependenteNotFoundException> {
            deletaDependenteUseCase.executa(ID_DEPENDENTE_INEXISTENTE)
        }

        verify(dependenteRepository, never()).delete(any())
    }

}