package br.unipar.plano.domain.contratos.usecases.impl

import br.unipar.plano.domain.centrais.usecases.impl.CentralNotFoundException
import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.ContratoRepository
import br.unipar.plano.domain.contratos.model.factories.CONTRATO_CO_ID
import br.unipar.plano.domain.contratos.model.factories.contrato
import br.unipar.plano.domain.contratos.model.factories.idContrato
import br.unipar.plano.domain.contratos.model.factories.planoTeste
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
        val novoPlano = planoTeste(UUID.fromString("4a4acdf0-4f59-4a2e-c315-a2b254e9e88a"))

        atualizaContratoUseCase.executa(CONTRATO_CO_ID) {
            it.with(plano = novoPlano)
        }

        verify(contratoRepository).save(argumentCaptor.capture())
        val contratoSalva = argumentCaptor.firstValue

        Assertions.assertEquals(CONTRATO_CO_ID, contratoSalva.id)
        Assertions.assertEquals(novoPlano, contratoSalva.plano)
    }

    @Test
    fun `deve garantir que o id permanece o mesmo mesmo que alterado pela funcao de transformacao`() {
        val novoPlano = planoTeste(UUID.fromString("4a4acdf0-4f59-4a2e-c315-a2b254e9e88a"))

        atualizaContratoUseCase.executa(CONTRATO_CO_ID) {
            it.with(
                id = idContrato(false),
                plano = novoPlano
            )
        }

        verify(contratoRepository).save(argumentCaptor.capture())
        val contratoSalva = argumentCaptor.firstValue

        Assertions.assertEquals(CONTRATO_CO_ID, contratoSalva.id)
        Assertions.assertEquals(novoPlano, contratoSalva.plano)
    }

    @Test
    fun `deve disparar uma excecao se o contrato nao existir`() {
        assertThrows<CentralNotFoundException> {
            atualizaContratoUseCase.executa(ID_CONTRATO_INEXISTENTE) {
                it.with()
            }
        }

        verify(contratoRepository, never()).save(any())
    }
}