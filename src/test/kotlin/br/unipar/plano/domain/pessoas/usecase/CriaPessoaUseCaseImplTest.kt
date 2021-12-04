package br.unipar.plano.domain.pessoas.usecase

import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.PessoaRepository
import br.unipar.plano.domain.pessoas.model.factories.PESSOA_CO_ID
import br.unipar.plano.domain.pessoas.model.factories.idPessoa
import br.unipar.plano.domain.pessoas.model.factories.pessoaTest
import br.unipar.plano.domain.pessoas.usecases.impl.CriaPessoaUseCaseImpl
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito

private val ID_PESSOA_INEXISTENTE = idPessoa(false)

class CriaPessoaUseCaseImplTest {

    private val pessoaRepository = mock<PessoaRepository>()
    private val criaPessoaUseCase = CriaPessoaUseCaseImpl(pessoaRepository)

    private val argumentCaptor = argumentCaptor<Pessoa>()

    @BeforeEach
    fun setUp() {
        whenever(pessoaRepository.existsById(eq(PESSOA_CO_ID))).thenReturn(true)
        whenever(
            pessoaRepository.existsById(
                eq(
                    ID_PESSOA_INEXISTENTE
                )
            )
        ).thenReturn(false)
        Mockito.`when`(pessoaRepository.save(any())).then { it.arguments[0] }
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novaPessoa = criaPessoaUseCase.cria(pessoaTest().with(idPessoa = ID_PESSOA_INEXISTENTE))

        verify(pessoaRepository).save(argumentCaptor.capture())
        val pessoaSalva = argumentCaptor.firstValue

        Assertions.assertEquals(novaPessoa, pessoaSalva)
    }

    @Test
    fun `deve disparar uma excecao se uma pessoa com o mesmo id ja existir`() {
        assertThrows<IllegalStateException> {
            criaPessoaUseCase.cria(pessoaTest())
        }

        verify(pessoaRepository, never()).save(any())
    }
}