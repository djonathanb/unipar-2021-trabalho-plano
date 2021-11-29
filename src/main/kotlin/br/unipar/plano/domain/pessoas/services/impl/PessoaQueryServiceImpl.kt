package br.unipar.plano.domain.pessoas.services.impl


import br.unipar.plano.domain.pessoas.usecases.impl.PessoaNotFoundException
import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.DependenteRepository
import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.services.PessoaQueryService
import org.springframework.stereotype.Service


@Service
class PessoaQueryServiceImpl(private val pessoaRepository: DependenteRepository): PessoaQueryService {

    override fun lista(): List<Pessoa> = pessoaRepository.findAll()

    override fun buscaPorId(idPessoa: IdPessoa): Pessoa = pessoaRepository.findById(idPessoa).orElseThrow{
        PessoaNotFoundException(idPessoa)
    }

}