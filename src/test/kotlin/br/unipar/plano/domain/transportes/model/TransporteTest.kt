package br.unipar.plano.domain.transportes.model

import br.unipar.plano.domain.centrais.model.StatusTransporte
import br.unipar.plano.domain.centrais.model.TipoTransporte
import br.unipar.plano.domain.centrais.model.factories.transporte
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate

class TransporteTest {

    @Test
    fun `deve criar Transporte com status igual a CRIADA`() {
        val transporte = transporte()
        assertEquals(StatusTransporte.PENDENTE, transporte.status)
    }

    @Test
    fun `deve permitir a alteracao de informacoes basicas da central`() {
        val novaDataSolicitacao= LocalDate.of(2021,10,1)
        val novoTipoTransporte = TipoTransporte.UTI_MOVEL

        val transporte = transporte()
        val novoTransporte = transporte.with(
            dataSolicitacao = novaDataSolicitacao,
            tipoTransporte =novoTipoTransporte
        )


        assertNotSame(transporte, novoTransporte)
        assertEquals(LocalDate.of(2021,11,1), novoTransporte.dataSolicitacao)
        assertEquals(TipoTransporte.AEREO, novoTransporte.tipoTransporte)
    }

    @Test
    fun `deve alterar o status do transpórte quando Aprovar`() {
        val transporte = transporte()
        val novoEstadotransporte = transporte.cancela()

        assertEquals(StatusTransporte.PENDENTE, transporte.status)
        assertEquals(StatusTransporte.APROVADO, novoEstadotransporte.status)
    }

    @Test
    fun `nao deve permitir a aprovação do transporte se estiver pendente `() {
        val mensagemEsperada = "Não é possível aprovar um Transporte com status PENDENTE"

        val transporte = transporte()
        assertEquals(StatusTransporte.PENDENTE, transporte.status)
        val message = assertThrows<IllegalStateException> {
            transporte.autoriza()
        }.message

        assertEquals(mensagemEsperada, message)
    }

    @Test
    fun `nao deve permitir o cancelamento do transporte se estiver cancelado `() {
        val mensagemEsperada = "Não é possível cancelar um Transporte com status CANCELADO"

        val transporte = transporte()
        assertEquals(StatusTransporte.CANCELADO, transporte.status)
        val message = assertThrows<IllegalStateException> {
            transporte.cancela()
        }.message

        assertEquals(mensagemEsperada, message)
    }


    companion object {

        @JvmStatic
        fun descredenciamentoStatusInvalidoSource() = listOf(
            Arguments.of(transporte(), "Não é possível cancelar um Transporte com status CANCELADO"),
            Arguments.of(transporte().autoriza().cancela(), "Não é possível aprovar um Transporte com status PENDENTE")
        )

    }

}