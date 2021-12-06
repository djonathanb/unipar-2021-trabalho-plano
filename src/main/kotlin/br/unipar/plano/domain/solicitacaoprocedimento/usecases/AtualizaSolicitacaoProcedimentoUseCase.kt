package br.unipar.plano.domain.solicitacaoprocedimento.usecases

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento

interface AtualizaSolicitacaoProcedimentoUseCase {

    fun executa(idSolicitacaoProcedimento: IdSolicitacaoProcedimento,
                transformation: (SolicitacaoProcedimento) -> SolicitacaoProcedimento)
}