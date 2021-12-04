package br.unipar.plano.domain.pessoas.usecases.impl

import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.model.PessoaRepository
import br.unipar.plano.domain.pessoas.usecases.DeletaPessoaUseCase
import org.springframework.stereotype.Service

@Service
class DeletaPessoaUseCaseImpl (private val pessoaRepository: PessoaRepository): DeletaPessoaUseCase {

    override fun executa(idPessoa: IdPessoa) {
        val pessoa = pessoaRepository.findById(idPessoa).orElseThrow {PessoaNotFoundException(idPessoa) }

        return pessoaRepository.delete(pessoa)
    }
}