package br.unipar.plano.domain.pessoas.usecases

import br.unipar.plano.domain.pessoas.model.IdDependente
import br.unipar.plano.domain.pessoas.model.Pessoa

interface AtualizaPessoaUseCase {

    fun executa(idPessoa: IdDependente, transformation: (Pessoa) -> Pessoa )

}