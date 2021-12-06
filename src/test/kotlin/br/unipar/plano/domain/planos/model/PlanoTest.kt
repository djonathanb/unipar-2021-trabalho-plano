package br.unipar.plano.domain.planos.model

import br.unipar.plano.domain.planos.model.factories.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlanoTest {

    @Test
    fun `deve criar um plano Nacional`() {
        val plano = plano()
        assertEquals(TipoAbrangencia.NACIONAL, plano.abrangencia)
    }

}