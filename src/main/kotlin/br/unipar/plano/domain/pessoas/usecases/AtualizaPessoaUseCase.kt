package br.unipar.plano.domain.pessoas.usecases


import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.model.Pessoa

interface AtualizaPessoaUseCase {

    fun executa(idPessoa: IdPessoa, transformation: (Pessoa) -> Pessoa )

}