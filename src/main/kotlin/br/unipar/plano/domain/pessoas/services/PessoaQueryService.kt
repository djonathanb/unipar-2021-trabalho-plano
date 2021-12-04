package br.unipar.plano.domain.pessoas.services

import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.model.Pessoa

interface PessoaQueryService {

    fun lista(): List<Pessoa>
    fun buscaPorId(idPessoa: IdPessoa): Pessoa
}