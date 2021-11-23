package br.unipar.plano.domain.centrais.model

import br.unipar.plano.domain.centrais.model.factories.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CentralTest {

    @Test
    fun `deve criar com status igual a CRIADA`() {
        val central = central()
        assertEquals(StatusCentral.CRIADA, central.status)
    }

    @Test
    fun `deve permitir a alteracao de informacoes basicas da central`() {
        val novoNome = "00.000.000/0001-00"
        val novoCnpj = "00.000.000/0001-00"
        val novaCidade = 1010100

        val central = central()
        val novoEstadoCentral = central.with(
            nome = novoNome,
            cnpj = novoCnpj,
            endereco = endereco(
                cidade = novaCidade
            )
        )

        assertNotSame(central, novoEstadoCentral)
        assertEquals(CENTRAL_CO_ID, novoEstadoCentral.id)
        assertEquals(CENTRAL_CO_STATUS, novoEstadoCentral.status)
        assertEquals(CENTRAL_CO_ENDERECO_LOGRADOURO, novoEstadoCentral.endereco.logradouro)
        assertEquals(novoCnpj, novoEstadoCentral.cnpj)
        assertEquals(novaCidade, novoEstadoCentral.endereco.cidade)
    }

    @Test
    fun `deve alterar o status da central quando credenciar`() {
        val central = central()
        val novoEstadoCentral = central.credencia()

        assertEquals(StatusCentral.CRIADA, central.status)
        assertEquals(StatusCentral.CREDENCIADA, novoEstadoCentral.status)
    }

    @Test
    fun `nao deve permitir recredenciar uma central ja credenciada`() {
        val mensagemEsperada = "Não é possível credenciar uma Central com status CREDENCIADA"

        val central = central().credencia()

        val message = assertThrows<IllegalStateException> {
            central.credencia()
        }.message

        assertEquals(mensagemEsperada, message)
    }

    @Test
    fun `deve alterar o status da central quando descredenciar`() {
        val central = central().credencia()
        val novoEstadoCentral = central.descredencia()

        assertEquals(StatusCentral.CREDENCIADA, central.status)
        assertEquals(StatusCentral.DESCREDENCIADA, novoEstadoCentral.status)
    }

    @ParameterizedTest
    @MethodSource("descredenciamentoStatusInvalidoSource")
    fun `nao deve permitir descredenciar uma central nao credenciada`(central: Central, mensagemEsperada: String) {
        val message = assertThrows<IllegalStateException> {
            central.descredencia()
        }.message

        assertEquals(mensagemEsperada, message)
    }

    companion object {

        @JvmStatic
        fun descredenciamentoStatusInvalidoSource() = listOf(
            Arguments.of(central(), "Não é possível descredenciar uma Central com status CRIADA"),
            Arguments.of(central().credencia().descredencia(), "Não é possível descredenciar uma Central com status DESCREDENCIADA")
        )

    }

}