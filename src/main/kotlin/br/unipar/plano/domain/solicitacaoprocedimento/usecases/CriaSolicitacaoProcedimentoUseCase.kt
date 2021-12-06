package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento

interface CriaSolicitacaoProcedimentoUseCase {
    fun executa(solicitacaoProcedimento: SolicitacaoProcedimento): SolicitacaoProcedimento
}