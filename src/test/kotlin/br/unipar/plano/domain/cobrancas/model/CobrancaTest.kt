package br.unipar.plano.domain.cobrancas.model

import br.unipar.plano.domain.cobrancas.model.factories.cobranca
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CobrancaTest {

    @Test
    fun `deve criar com status igual a ABERTO`() {
        val cobranca = cobranca()
        assertEquals(StatusCobranca.ABERTO, cobranca.status)
    }

    @Test
    fun `deve permitir o cancelamento de cobranca`() {
        val cobranca = cobranca()
        val cobrancaCancelada = cobranca.cancelar()
        assertEquals(StatusCobranca.CANCELADO, cobrancaCancelada.status)
        assertNotNull(cobrancaCancelada.dataCancelamento)
    }

    @Test
    fun `nao deve permitir o cancelamento de cobranca`() {
        val cobranca = cobranca(status = StatusCobranca.CANCELADO)
        assertThrows<IllegalStateException> { cobranca.cancelar() }
    }

}