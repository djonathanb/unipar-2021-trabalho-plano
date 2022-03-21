package br.unipar.plano.domain.solicitacaoprocedimento.service

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoDTO
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoDetailsDTO
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoSummaryDTO

interface SolicitacaoProcedimentoService {

    fun insert(dto: SolicitacaoProcedimentoDTO): IdSolicitacaoProcedimento
    fun liberarSolicitacao(idSolicitacaoProcedimento: IdSolicitacaoProcedimento)
    fun rejeitarSolicitacao(idSolicitacaoProcedimento: IdSolicitacaoProcedimento, descricaoRejeicao: String)
    fun lista(): List<SolicitacaoProcedimentoSummaryDTO>
    fun buscaPorId(idSolicitacaoProcedimento: IdSolicitacaoProcedimento): SolicitacaoProcedimentoDetailsDTO
    fun deleta(idSolicitacaoProcedimento: IdSolicitacaoProcedimento)
}