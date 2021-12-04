package br.unipar.plano.domain.contratos.usecases.impl


import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.ContratoRepository
import br.unipar.plano.domain.contratos.model.factories.CONTRATO_CO_ID
import br.unipar.plano.domain.contratos.model.factories.contrato
import br.unipar.plano.domain.contratos.model.factories.idContrato
import br.unipar.plano.domain.contratos.planos.model.factories.idPlano
import br.unipar.plano.domain.contratos.planos.model.factories.plano
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.util.*


private val ID_CONTRATO_INEXISTENTE = idContrato(false)

class AtualizaContratoUseCaseImplTest {

    private val contratoRepository = mock<ContratoRepository>()
    private val atualizaContratoUseCase = AtualizaContratoUseCaseImpl(contratoRepository)

    private val argumentCaptor = argumentCaptor<Contrato>()

    @BeforeEach
    fun setUp() {
        whenever(contratoRepository.findById(eq(CONTRATO_CO_ID))).thenReturn(Optional.of(contrato()))
        whenever(contratoRepository.findById(eq(ID_CONTRATO_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novoVencimento = LocalDate.of(2021,8,18)

        atualizaContratoUseCase.executa(CONTRATO_CO_ID) {
            it.with(dataContratoFinal = novoVencimento)
        }

        verify(contratoRepository).save(argumentCaptor.capture())
        val contratoSalva = argumentCaptor.firstValue

        Assertions.assertEquals(CONTRATO_CO_ID, contratoSalva.id)
        Assertions.assertEquals(novoVencimento, contratoSalva.dataContratoFinal)
    }

    @Test
    fun `deve garantir que o id permanece o mesmo mesmo que alterado pela funcao de transformacao`() {
        val novoVencimento = LocalDate.of(2021,8,18)

        atualizaContratoUseCase.executa(CONTRATO_CO_ID) {
            it.with(
                id = idContrato(false),
                dataContratoFinal = novoVencimento
            )
        }

        verify(contratoRepository).save(argumentCaptor.capture())
        val contratoSalva = argumentCaptor.firstValue

        Assertions.assertEquals(CONTRATO_CO_ID, contratoSalva.id)
        Assertions.assertEquals(novoVencimento, contratoSalva.dataContratoFinal)
    }

    @Test
    fun `deve garantir que o planos seja atualizado`() {
        val plano = plano(idPlano(), valorbase = 110.0)

        atualizaContratoUseCase.executa(CONTRATO_CO_ID) {
            it.with(
                dataContratoFinal = LocalDate.now(),
                plano = plano
            )
        }

        verify(contratoRepository).save(argumentCaptor.capture())
        val contratoSalva = argumentCaptor.firstValue

        Assertions.assertEquals(CONTRATO_CO_ID, contratoSalva.id)
        Assertions.assertEquals(plano.valorbase, contratoSalva.plano.valorbase)
    }

    @Test
    fun `deve disparar uma excecao se o plano for menor`() {
        val plano = plano( idPlano(), valorbase = 10.0)
        assertThrows<ContratoDowngradeException> {
            atualizaContratoUseCase.executa(CONTRATO_CO_ID) {
                it.with(
                    plano = plano
                )
            }
        }

        verify(contratoRepository, never()).save(any())
    }

    @Test
    fun `deve disparar uma excecao se o contrato nao existir`() {
        assertThrows<ContratoNotFoundException> {
            atualizaContratoUseCase.executa(ID_CONTRATO_INEXISTENTE) {
                it.with()
            }
        }

        verify(contratoRepository, never()).save(any())
    }
}