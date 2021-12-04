package br.unipar.plano.domain.credenciamentos.model.prestadorMedico

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.StatusCentral
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.prestadorMedico
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PrestadorMedicoTest {

    @Test
    fun `deve criar com status igual o CRIADO`() {
        val prestadorMedico = prestadorMedico()
        assertEquals(StatusMedico.CRIADO, prestadorMedico.status)
    }

    @Test
    fun `deve permitir a alteracao de informacoes basico de prestador medico`() {
        val novoNome = "00.000.000/0001-00"
        val novoCrm = "00.000.000/0001-00"

        val prestadorMedico = prestadorMedico()
        val novoEstadoPrestadorMedico = prestadorMedico.with(
            nome = novoNome,
            crm = novoCrm
        )

        assertNotSame(prestadorMedico, novoEstadoPrestadorMedico)
        assertEquals(novoCrm, novoEstadoPrestadorMedico.crm)
        assertEquals(novoNome, novoEstadoPrestadorMedico.nome)
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