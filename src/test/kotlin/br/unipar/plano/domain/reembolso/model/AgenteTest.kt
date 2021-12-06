package br.unipar.plano.domain.reembolso.model

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.reembolso.model.factories.carteirinha
import br.unipar.plano.domain.reembolso.model.factories.plano
import br.unipar.plano.domain.reembolso.model.factories.reembolso
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class AgenteTest {

    @Test
    fun `deve alterar o status do reembolso quando autorizado`() {
        val reembolso = reembolso()
        val novoEstadoReembolso = reembolso.autoriza()

        Assertions.assertEquals(StatusReembolso.EM_ANALIZE, reembolso.statusReembolso)
        Assertions.assertEquals(StatusReembolso.APROVADO, novoEstadoReembolso.statusReembolso)
    }

//    @Test
//    fun `deve alterar o status do reembolso quando rejeitado`() {
//        val reembolso = reembolso().autoriza()
//        val novoEstadoReembolso = reembolso.rejeita()
//
//        Assertions.assertEquals(StatusReembolso.APROVADO, reembolso.statusReembolso)
//        Assertions.assertEquals(StatusReembolso.REJEITADO, novoEstadoReembolso.statusReembolso)
//    }
}