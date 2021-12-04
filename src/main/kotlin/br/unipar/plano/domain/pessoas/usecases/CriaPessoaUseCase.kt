package br.unipar.plano.domain.pessoas.usecases

import br.unipar.plano.domain.pessoas.model.Pessoa

interface CriaPessoaUseCase {
    fun cria(pessoa: Pessoa): Pessoa
}