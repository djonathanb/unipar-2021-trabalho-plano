package br.unipar.plano.domain.pessoas.usecases.impl

import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.PessoaRepository
import br.unipar.plano.domain.pessoas.usecases.CriaPessoaUseCase
import org.springframework.stereotype.Service

@Service
class CriaPessoaUseCaseImpl (private val pessoaRepository: PessoaRepository): CriaPessoaUseCase {

    override fun cria(pessoa: Pessoa): Pessoa {
        if (pessoaRepository.existsById(pessoa.idPessoa)) {
            throw IllegalStateException("Já existe uma pessoa cadastrada para o ID ${pessoa.idPessoa}")
        }
        return pessoaRepository.save(pessoa)
    }
}