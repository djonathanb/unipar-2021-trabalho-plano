package br.unipar.plano.domain.pessoas.services

import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.interfaces.rest.pessoas.PessoaDTO
import br.unipar.plano.interfaces.rest.pessoas.PessoaDetailsDTO

interface PessoaApplicationService {

    fun cria(pessoaDTO: PessoaDTO): IdPessoa
    fun lista(): List<PessoaDTO>
    fun buscaPorId(idPessoa: IdPessoa): PessoaDetailsDTO
    fun deleta(idPessoa: IdPessoa)
    fun atualiza(idPessoa: IdPessoa, pessoaDTO: PessoaDTO)
}