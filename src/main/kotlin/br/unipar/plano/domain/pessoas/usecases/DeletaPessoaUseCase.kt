package br.unipar.plano.domain.pessoas.usecases


import br.unipar.plano.domain.pessoas.model.IdPessoa

interface DeletaPessoaUseCase {

    fun executa(idPessoa: IdPessoa)
}