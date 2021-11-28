package br.unipar.plano.domain.pessoas.usecases.impl

import br.unipar.plano.domain.pessoas.model.IdDependente
import br.unipar.plano.domain.pessoas.model.DependenteRepository
import br.unipar.plano.domain.pessoas.usecases.DeletaPessoaUseCase
import org.springframework.stereotype.Service

@Service
class DeletaPessoaUseCaseImpl (private val pessoaRepository: DependenteRepository): DeletaPessoaUseCase {

    override fun executa(idPessoa: IdDependente) {
        val pessoa = pessoaRepository.findById(idPessoa).orElseThrow {PessoaNotFoundException(idPessoa) }

        return pessoaRepository.delete(pessoa)
    }
}