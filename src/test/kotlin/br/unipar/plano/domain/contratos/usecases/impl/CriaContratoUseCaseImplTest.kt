package br.unipar.plano.domain.contratos.usecases.impl

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.ContratoRepository
import br.unipar.plano.domain.contratos.model.factories.CONTRATO_CO_ID
import br.unipar.plano.domain.contratos.model.factories.contrato
import br.unipar.plano.domain.contratos.model.factories.idContrato
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

private val ID_CONTRATO_INEXISTENTE = idContrato(false)

class CriaContratoUseCaseImplTest {

    private val contratoRepository = mock<ContratoRepository>()
    private val criaContratoUseCase = CriaContratoUseCaseImpl(contratoRepository)

    private val argumentCaptor = argumentCaptor<Contrato>()

    @BeforeEach
    fun setUp() {
        whenever(contratoRepository.existsById(eq(CONTRATO_CO_ID))).thenReturn(true)
        whenever(
            contratoRepository.existsById(
                eq(
                    ID_CONTRATO_INEXISTENTE
                )
            )
        ).thenReturn(false)
        Mockito.`when`(contratoRepository.save(any())).then { it.arguments[0] }
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novaCentral = criaContratoUseCase.cria(contrato().with(id = ID_CONTRATO_INEXISTENTE))

        verify(contratoRepository).save(argumentCaptor.capture())
        val centralSalva = argumentCaptor.firstValue

        Assertions.assertEquals(novaCentral, centralSalva)
    }

    @Test
    fun `deve disparar uma excecao se uma central com o mesmo id ja existir`() {
        assertThrows<IllegalStateException> {
            criaContratoUseCase.cria(contrato())
        }

        verify(contratoRepository, never()).save(any())
    }
}