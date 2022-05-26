package br.unipar.plano.domain.credenciamentos.model.prestadorMedico

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.StatusCentral
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.StatusClinicaHospital
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
        assertEquals(StatusMedico.CRIADA, prestadorMedico.status)
    }

    @Test
    fun `deve permitir a alteracao de informacoes basico de prestador medico`() {
        val novoNome = "John Lenon Escrevenon"
        val novoCrm = "54321/SP"

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
    fun `deve alterar o status do prestador medico quando credenciar`() {
        val prestadorMedico = prestadorMedico()
        val novoEstadoPrestadorMedico = prestadorMedico.credencia()

        assertEquals(StatusMedico.CRIADA, prestadorMedico.status)
        assertEquals(StatusMedico.CREDENCIADA, novoEstadoPrestadorMedico.status)
    }

    @Test
    fun `nao deve permitir recredenciar o prestador médico com status credenciado`() {
        val mensagemEsperada = "Não é possível credenciar um médico com status CREDENCIADA"

        val prestadorMedico = prestadorMedico().credencia()

        val message = assertThrows<IllegalStateException> {
            prestadorMedico.credencia()
        }.message

        assertEquals(mensagemEsperada, message)
    }

    @Test
    fun `deve alterar o status do prestador medico quando descredenciar`() {
        val prestadorMedico = prestadorMedico().credencia()
        val novoEstadoPrestadorMedico = prestadorMedico.descredencia()

        assertEquals(StatusMedico.CREDENCIADA, prestadorMedico.status)
        assertEquals(StatusMedico.DESCREDENCIADA, novoEstadoPrestadorMedico.status)
    }

    @ParameterizedTest
    @MethodSource("descredenciamentoStatusInvalidoSource")
    fun `nao deve permitir descredenciar um prestador médico ao credenciada`(prestadorMedico: PrestadorMedico, mensagemEsperada: String) {
        val message = assertThrows<IllegalStateException> {
            prestadorMedico.descredencia()
        }.message
        assertEquals(mensagemEsperada, message)
    }

    companion object {

        @JvmStatic
        fun descredenciamentoStatusInvalidoSource() = listOf(
            Arguments.of(prestadorMedico(), "Não é possível descredenciar um médico com status CRIADA"),
            Arguments.of(prestadorMedico().credencia().descredencia(), "Não é possível descredenciar um médico com status DESCREDENCIADA")
        )

    }

}