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

private val ID_CENTRAL_INEXISTENTE = idCentral(false)

class CriaTransporteUseCaseImplTest {

    private val centralRepository = mock<CentralRepository>()
    private val criaCentralUseCase = CriaCentralUseCaseImpl(centralRepository)

    private val argumentCaptor = argumentCaptor<Central>()

    @BeforeEach
    fun setUp() {
        whenever(centralRepository.existsById(eq(CENTRAL_CO_ID))).thenReturn(true)
        whenever(centralRepository.existsById(eq(ID_CENTRAL_INEXISTENTE))).thenReturn(false)
        `when`(centralRepository.save(any())).then { it.arguments[0] }
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novaCentral = criaCentralUseCase.executa(central().with(id = ID_CENTRAL_INEXISTENTE))

        verify(centralRepository).save(argumentCaptor.capture())
        val centralSalva = argumentCaptor.firstValue

        assertEquals(novaCentral, centralSalva)
    }

    @Test
    fun `deve disparar uma excecao se uma central com o mesmo id ja existir`() {
        assertThrows<IllegalStateException> {
            criaCentralUseCase.executa(central())
        }

        verify(centralRepository, never()).save(any())
    }

}