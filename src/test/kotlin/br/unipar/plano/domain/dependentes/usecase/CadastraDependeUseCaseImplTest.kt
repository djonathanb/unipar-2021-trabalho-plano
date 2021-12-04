package br.unipar.plano.domain.dependentes.usecase

import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.dependentes.model.DependenteRepository
import br.unipar.plano.domain.dependentes.model.factories.DEPENDENTE_CO_ID
import br.unipar.plano.domain.dependentes.model.factories.dependente
import br.unipar.plano.domain.dependentes.model.factories.idDependente
import br.unipar.plano.domain.dependentes.usecase.impl.CadastraDependenteUseCaseImpl
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

private val ID_DEPENDENTE_INEXISTENTE = idDependente(false)

class CadastraDependeUseCaseImplTest {

    private val dependenteRepository = mock<DependenteRepository>()
    private val criaDependenteUseCase = CadastraDependenteUseCaseImpl(dependenteRepository)

    private val argumentCaptor = argumentCaptor<Dependente>()

    @BeforeEach
    fun setUp() {
        whenever(dependenteRepository.existsById(eq(DEPENDENTE_CO_ID))).thenReturn(true)
        whenever(
            dependenteRepository.existsById(
                eq(
                    ID_DEPENDENTE_INEXISTENTE
                )
            )
        ).thenReturn(false)
        Mockito.`when`(dependenteRepository.save(any())).then { it.arguments[0] }
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novoDependente = criaDependenteUseCase.cadastra(dependente().with(idDependente = ID_DEPENDENTE_INEXISTENTE))

        verify(dependenteRepository).save(argumentCaptor.capture())
        val dependenteSalvo = argumentCaptor.firstValue

        Assertions.assertEquals(novoDependente, dependenteSalvo)
    }

    @Test
    fun `deve disparar uma excecao se uma pessoa com o mesmo id ja existir`() {
        assertThrows<IllegalStateException> {
            criaDependenteUseCase.cadastra(dependente())
        }

        verify(dependenteRepository, never()).save(any())
    }

}