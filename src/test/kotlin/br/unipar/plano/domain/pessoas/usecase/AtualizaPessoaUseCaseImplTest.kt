package br.unipar.plano.domain.pessoas.usecase

import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.PessoaRepository
import br.unipar.plano.domain.pessoas.model.factories.PESSOA_CO_ID
import br.unipar.plano.domain.pessoas.model.factories.idPessoa
import br.unipar.plano.domain.pessoas.model.factories.pessoaTest
import br.unipar.plano.domain.pessoas.usecases.impl.AtualizaPessoaUseCaseImpl
import br.unipar.plano.domain.pessoas.usecases.impl.PessoaNotFoundException
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_PESSOA_INEXISTENTE = idPessoa(false)

class AtualizaPessoaUseCaseImplTest {
    private val pessoaRepository = mock<PessoaRepository>()
    private val atualizaPessoaUseCase = AtualizaPessoaUseCaseImpl(pessoaRepository)

    private val argumentCaptor = argumentCaptor<Pessoa>()

    @BeforeEach
    fun setUp() {
        whenever(pessoaRepository.findById(eq(PESSOA_CO_ID))).thenReturn(Optional.of(pessoaTest()))
        whenever(pessoaRepository.findById(eq(ID_PESSOA_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve atualizar os dados informados pela funcao de transformacao`() {
        val novoNome = "JERISVALDO PEREIRA"

        atualizaPessoaUseCase.executa(PESSOA_CO_ID) {
            it.with(nome = novoNome)
        }

        verify(pessoaRepository).save(argumentCaptor.capture())
        val pessoaSalva = argumentCaptor.firstValue

        Assertions.assertEquals(PESSOA_CO_ID, pessoaSalva.idPessoa)
        Assertions.assertEquals(novoNome, pessoaSalva.nome)
    }

    @Test
    fun `deve garantir que o id permanece o mesmo mesmo que alterado pela funcao de transformacao`() {
        val novoNome = "INVIOMAR FRANCO"

        atualizaPessoaUseCase.executa(PESSOA_CO_ID) {
            it.with(
                idPessoa = idPessoa(false),
                nome = novoNome
            )
        }

        verify(pessoaRepository).save(argumentCaptor.capture())
        val pessoaSalva = argumentCaptor.firstValue

        Assertions.assertEquals(PESSOA_CO_ID, pessoaSalva.idPessoa)
        Assertions.assertEquals(novoNome, pessoaSalva.nome)
    }

    @Test
    fun `deve disparar uma excecao se a pessoa nao existir`() {
        assertThrows<PessoaNotFoundException> {
            atualizaPessoaUseCase.executa(ID_PESSOA_INEXISTENTE) {
                it.with()
            }
        }

        verify(pessoaRepository, never()).save(any())
    }

}