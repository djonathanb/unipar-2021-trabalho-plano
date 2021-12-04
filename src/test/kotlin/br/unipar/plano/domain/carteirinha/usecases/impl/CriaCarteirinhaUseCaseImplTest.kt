package br.unipar.plano.domain.carteirinha.usecases.impl

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.CarteirinhaRepository
import br.unipar.plano.domain.carteirinha.model.StatusCarteirinha
import br.unipar.plano.domain.carteirinha.model.factories.*
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.util.*

class CriaCarteirinhaUseCaseImplTest {

    private val carteirinhaRepository = mock<CarteirinhaRepository>()
    private val criaCarteirinhaUseCase = CriaCarteirinhaUseCaseImpl(carteirinhaRepository)

    private val argumentCaptor = argumentCaptor<Carteirinha>()

    @BeforeEach
    fun setUp() {
        whenever(carteirinhaRepository.findById(eq(CARTEIRINHA_NUMERO_CARTEIRINHA_VALIDO))).thenReturn(Optional.empty())
        whenever(carteirinhaRepository.findById(eq(CARTEIRINHA_NUMERO_CARTEIRINHA_INVALIDO))).thenReturn(Optional.empty())
        whenever(carteirinhaRepository.findByIdUsuario(eq(CARTEIRINHA_ID_USUARIO_JA_CADASTRADO))).thenReturn(Optional.of(carteirinha()))
        whenever(carteirinhaRepository.findByIdUsuario(eq(CARTEIRINHA_ID_USUARIO))).thenReturn(Optional.empty())
        whenever(carteirinhaRepository.findById(eq(CARTEIRINHA_NUMERO_CARTEIRINHA_CADASTRADO))).thenReturn(Optional.of(carteirinha()))
    }

    @Test
    fun `Nao deve permitir a criacao de uma carteirinha para um usuario que ja possua uma valida`() {
        val mensagemEsperada = "Usuário $CARTEIRINHA_ID_USUARIO_JA_CADASTRADO já possui uma carteirinha válida: $CARTEIRINHA_NUMERO_CARTEIRINHA_VALIDO"

        val message = assertThrows<Exception> {
            criaCarteirinhaUseCase.cria(carteirinha(idUsuario = CARTEIRINHA_ID_USUARIO_JA_CADASTRADO))
        }.message

        assertEquals(mensagemEsperada, message)
    }

    @Test
    fun `Nao deve permitir a insercao de carteirinha duplicada`() {
        val mensagemEsperada = "A carteirinha $CARTEIRINHA_NUMERO_CARTEIRINHA_CADASTRADO já está cadastrada"

        val message = assertThrows<Exception> {
            criaCarteirinhaUseCase.cria(carteirinha(numeroCarteirinha = CARTEIRINHA_NUMERO_CARTEIRINHA_CADASTRADO))
        }.message

        assertEquals(mensagemEsperada, message)
    }



//    @Test
//    fun `Deve salvar a carteirinha e registrar as informacoes corretas`() {
//        criaCarteirinhaUseCase.cria(carteirinha(numeroCarteirinha = CARTEIRINHA_NUMERO_CARTEIRINHA_VALIDO))
//
//        verify(carteirinhaRepository).save(argumentCaptor.capture())
//        val carteirinhaSalva = argumentCaptor.firstValue;
//
//        assertEquals(CARTEIRINHA_NUMERO_CARTEIRINHA_VALIDO, carteirinhaSalva.numeroCarteirinha)
//        assertEquals(CARTEIRINHA_ID_USUARIO, carteirinhaSalva.idUsuario)
//        assertEquals(LocalDate.now(), carteirinhaSalva.dataEmissao)
//        assertEquals(LocalDate.now().plusYears(3), carteirinhaSalva.dataVencimento)
//        assertEquals(StatusCarteirinha.ENTREGA_PENDENTE, carteirinhaSalva.status)
//        assertEquals(null, carteirinhaSalva.dataEntrega)
//
//    }

}