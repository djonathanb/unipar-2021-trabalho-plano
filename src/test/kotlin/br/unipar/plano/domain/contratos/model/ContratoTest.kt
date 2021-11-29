package br.unipar.plano.domain.contratos.model

import br.unipar.plano.domain.contratos.model.factories.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate


class ContratoTest {


    @Test
    fun `deve criar com status igual a ATIVO`() {
        val contrato = contrato()
        Assertions.assertEquals(StatusContrato.ATIVO, contrato.status)
    }

    @Test
    fun `deve permitir a alteracao de informacoes basicas do contrato`() {
        val novoVencimento = LocalDate.now()

        val contrato = contrato()
        val novoEstadoContrato = contrato.with(
            dataContratoFinal = novoVencimento,

        )

        Assertions.assertNotSame(contrato, novoEstadoContrato)
        Assertions.assertEquals(CONTRATO_CO_STATUS, novoEstadoContrato.status)
        Assertions.assertEquals(novoVencimento, novoEstadoContrato.dataContratoFinal)
    }

    @Test
    fun `deve alterar o status do contrato quando cancelado`() {
        val contrato = contrato()
        val novoEstadoContrato = contrato.cancela()

        Assertions.assertEquals(StatusContrato.ATIVO, contrato.status)
        Assertions.assertEquals(StatusContrato.CANCELADO, novoEstadoContrato.status)
    }

    @ParameterizedTest
    @MethodSource("descredenciamentoStatusInvalidoSource")
    fun `nao deve permitir cancelar um contrato cancelado`(contrato: Contrato, mensagemEsperada: String) {
        val message = assertThrows<IllegalStateException> {
            contrato.cancela()
        }.message

        Assertions.assertEquals(mensagemEsperada, message)
    }

    companion object {

        @JvmStatic
        fun descredenciamentoStatusInvalidoSource() = listOf(
            Arguments.of(contrato().cancela(), "Não é possível cancelar um Contrato com status CANCELADO")
        )

    }
}