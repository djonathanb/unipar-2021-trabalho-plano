package br.unipar.plano.domain.transportes.model

import br.unipar.plano.domain.transportes.model.factories.transporte
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.provider.Arguments
import java.time.LocalDate

class TransporteTest {

    @Test
    fun `deve criar com status igual a PENDENTE`() {
        val transporte = transporte()
        assertEquals(StatusTransporte.PENDENTE, transporte.status)
    }

    @Test
    fun `deve criar com tipo transporte igual a AEREO`() {
        val transporte = transporte(tipoTransporte = TipoTransporte.AEREO)
        assertEquals(StatusTransporte.PENDENTE, transporte.status)
    }

    @Test
    fun `deve criar com tipo transporte igual a AMBULANCIA`() {
        val transporte = transporte(tipoTransporte = TipoTransporte.AMBULANCIA)
        assertEquals(StatusTransporte.PENDENTE, transporte.status)
    }

    @Test
    fun `deve criar com tipo transporte igual a UTI_MOVEL`() {
        val transporte = transporte(tipoTransporte = TipoTransporte.UTI_MOVEL)
        assertEquals(StatusTransporte.PENDENTE, transporte.status)
    }

    @Test
    fun `deve criar com a dataSolicitacao igual a data atual`() {
        val transporte = transporte()
        assertEquals(transporte.dataSolicitacao, LocalDate.now())
    }

    @Test
    fun `deve permitir a alteracao de informacoes basicas do transporte`() {
        val novaDataSolicitacao = LocalDate.of(2021, 10, 1)
        val novoTipoTransporte = TipoTransporte.UTI_MOVEL

        val transporte = transporte()
        val novoTransporte = transporte.with(
            dataSolicitacao = novaDataSolicitacao,
            tipoTransporte = novoTipoTransporte
        )

        assertNotSame(transporte, novoTransporte)
        assertEquals(LocalDate.of(2021, 10, 1), novoTransporte.dataSolicitacao)
        assertEquals(TipoTransporte.UTI_MOVEL, novoTransporte.tipoTransporte)
    }

    @Test
    fun `deve alterar o status do transpórte quando Aprovar`() {
        val transporte = transporte()
        val novoEstadotransporte = transporte.autoriza()

        assertEquals(StatusTransporte.PENDENTE, transporte.status)
        assertEquals(StatusTransporte.APROVADO, novoEstadotransporte.status)
    }

//    @Test
//    fun `nao deve permitir o cancelamento do transporte se estiver cancelado `() {
//        val mensagemEsperada = "Não é possível cancelar um Transporte com status CANCELADO"
//
//        val transporte = transporte()
//        assertEquals(StatusTransporte.PENDENTE, transporte.status)
//        transporte.cancela()
//        assertEquals(StatusTransporte.CANCELADO, transporte.status)
//    }

    companion object {
        @JvmStatic
        fun descredenciamentoStatusInvalidoSource() = listOf(
            Arguments.of(transporte().cancela(), "Não é possível cancelar um Transporte com status CANCELADO"),
            Arguments.of(transporte().autoriza(), "Não é possível aprovar um Transporte com status PENDENTE")
        )
    }

}