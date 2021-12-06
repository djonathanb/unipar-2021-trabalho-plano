package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento

interface DeletaSolicitacaoProcedimentoUseCase {

    fun executa(idSolicitacaoProcedimento: IdSolicitacaoProcedimento)

}
