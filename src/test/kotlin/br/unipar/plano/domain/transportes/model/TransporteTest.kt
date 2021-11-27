package br.unipar.plano.domain.centrais.model

import br.unipar.plano.domain.centrais.model.factories.*
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

}