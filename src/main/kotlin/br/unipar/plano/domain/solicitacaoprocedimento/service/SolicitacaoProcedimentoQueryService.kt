package br.unipar.plano.domain.solicitacaoprocedimento.service

import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Procedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento

interface SolicitacaoProcedimentoQueryService {
    fun lista(): List<SolicitacaoProcedimento>
    fun buscaPorId(idSolicitacaoProcedimento: IdSolicitacaoProcedimento): SolicitacaoProcedimento
    fun findByProcedimentoAndStatusSolicitacaoEquals(
        procedimento: Procedimento,
        statusSolicitacao: StatusSolicitacaoProcedimento
    ): List<SolicitacaoProcedimento>;

    fun findByProcedimento_Id(idProcedimento: IdProcedimento): List<SolicitacaoProcedimento>
}