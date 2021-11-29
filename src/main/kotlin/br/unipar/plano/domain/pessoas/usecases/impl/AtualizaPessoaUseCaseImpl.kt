package br.unipar.plano.domain.pessoas.usecases.impl


import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.DependenteRepository
import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.usecases.AtualizaPessoaUseCase
import org.springframework.stereotype.Service

@Service
class AtualizaPessoaUseCaseImpl(private val pessoaRepository: DependenteRepository) : AtualizaPessoaUseCase {

    override fun executa(idPessoa: IdPessoa, transformation: (Pessoa) -> Pessoa) {
        val pessoa = pessoaRepository.findById(idPessoa).orElseThrow {PessoaNotFoundException(idPessoa) }

        pessoaRepository.save(transformation(pessoa).with(idPessoa = idPessoa))
    }
}
