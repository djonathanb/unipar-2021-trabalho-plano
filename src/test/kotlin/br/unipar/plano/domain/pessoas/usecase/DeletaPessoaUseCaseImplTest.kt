package br.unipar.plano.domain.pessoas.usecase


import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.PessoaRepository
import br.unipar.plano.domain.pessoas.model.factories.PESSOA_CO_ID
import br.unipar.plano.domain.pessoas.model.factories.idPessoa
import br.unipar.plano.domain.pessoas.model.factories.pessoaTest
import br.unipar.plano.domain.pessoas.usecases.impl.DeletaPessoaUseCaseImpl
import br.unipar.plano.domain.pessoas.usecases.impl.PessoaNotFoundException
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

private val ID_PESSOA_INEXISTENTE = idPessoa(false)

class DeletaPessoaUseCaseImplTest {

    private val pessoaRepository = mock<PessoaRepository>()
    private val deletaPessoaUseCase = DeletaPessoaUseCaseImpl(pessoaRepository)

    private val argumentCaptor = argumentCaptor<Pessoa>()

    @BeforeEach
    fun setUp() {
        whenever(pessoaRepository.findById(eq(PESSOA_CO_ID))).thenReturn(Optional.of(pessoaTest()))
        whenever(pessoaRepository.findById(eq(ID_PESSOA_INEXISTENTE))).thenReturn(Optional.empty())
    }

    @Test
    fun `deve deletar a central informada`() {
        deletaPessoaUseCase.executa(PESSOA_CO_ID)

        verify(pessoaRepository).delete(argumentCaptor.capture())
        val pessoaDeletata = argumentCaptor.firstValue

        Assertions.assertEquals(PESSOA_CO_ID, pessoaDeletata.idPessoa)
    }

    @Test
    fun `deve disparar uma excecao se a pessoa nao existir`() {
        assertThrows<PessoaNotFoundException> {
            deletaPessoaUseCase.executa(ID_PESSOA_INEXISTENTE)
        }

        verify(pessoaRepository, never()).delete(any())
    }
}