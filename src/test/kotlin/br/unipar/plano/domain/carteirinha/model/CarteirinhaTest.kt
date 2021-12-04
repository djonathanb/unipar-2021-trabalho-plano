package br.unipar.plano.domain.carteirinha.model

import br.unipar.plano.domain.carteirinha.model.factories.carteirinha
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CarteirinhaTest {

    @Test
    fun `Deve criar com status igual a PENDENTE_ENTREGA`() {
        val carteirinha = carteirinha()
        assertEquals(StatusCarteirinha.ENTREGA_PENDENTE, carteirinha.status)
    }

    @Test
    fun `Nao deve permitir validacao de carteirinha com status ENTREGA_PENDENTE`() {
        val mensagemEsperada = "Carteirinha Inválida:\n" +
                " Status: ENTREGA_PENDENTE"

        val message = assertThrows<Exception> {
            carteirinha().validate()
        }.message

        assertEquals(mensagemEsperada, message)

    }

    @Test
    fun `Realizar a entrega da carteirinha`() {
        val carteirinha = carteirinha().registrarEntrega();
        assertEquals(StatusCarteirinha.VALIDA, carteirinha.status)
    }

    @Test
    fun `Registrar o extravio da Carteirinha`() {
        val carteirinha = carteirinha().registrarExtravio();
        assertEquals(StatusCarteirinha.EXTRAVIADA, carteirinha.status)
    }

}