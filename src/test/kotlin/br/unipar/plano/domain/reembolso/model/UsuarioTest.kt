package br.unipar.plano.domain.reembolso.model

import br.unipar.plano.domain.reembolso.model.factories.reembolso
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class UsuarioTest {

    @Test
    fun `deve criar com status igual a EM_ANALIZE`() {
        val reembolso = reembolso()
        println("1: " + UUID.randomUUID())
        println("2: " + UUID.fromString("4f96271f-2c02-4000-9324-b8c17f5314a7"))
        Assertions.assertEquals(StatusReembolso.EM_ANALIZE, reembolso.statusReembolso)
    }
}