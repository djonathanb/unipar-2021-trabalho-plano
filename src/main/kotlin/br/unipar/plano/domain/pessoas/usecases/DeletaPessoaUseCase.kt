package br.unipar.plano.domain.pessoas.usecases

import br.unipar.plano.domain.pessoas.model.IdDependente

interface DeletaPessoaUseCase {

    fun executa(idPessoa: IdDependente)
}