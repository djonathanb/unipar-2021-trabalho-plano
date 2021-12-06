package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento

class SolicitacaoProcedimentoNotFoundException(idSolicitacaoProcedimento: IdSolicitacaoProcedimento)
    : NotFoundException("Solicitacao com id ${idSolicitacaoProcedimento.id} não encontrada")