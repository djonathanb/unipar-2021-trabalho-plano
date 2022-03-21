package br.unipar.plano.domain.solicitacaoprocedimento.usecases

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento

interface RejeitaSolicitacaoProcedimentoUseCase {

    fun executa(idSolicitacaoProcedimento: IdSolicitacaoProcedimento, descricaoRejeicao: String)

}
