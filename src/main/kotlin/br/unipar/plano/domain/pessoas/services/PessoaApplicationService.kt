package br.unipar.plano.domain.pessoas.services

import br.unipar.plano.domain.pessoas.model.IdDependente
import br.unipar.plano.interfaces.rest.pessoas.PessoaDTO
import br.unipar.plano.interfaces.rest.pessoas.PessoaDetailsDTO


interface PessoaApplicationService {

    fun cria(pessoaDTO: PessoaDTO): IdDependente
    fun lista(): List<PessoaDTO>
    fun buscaPorId(idPessoa: IdDependente): PessoaDetailsDTO
    fun deleta(idPessoa: IdDependente)
    fun atualiza(idPessoa: IdDependente, pessoaDTO: PessoaDTO)
}