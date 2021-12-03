package br.unipar.plano.domain.solicitacaoprocedimento.model.factories

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import java.time.LocalDate
import java.util.*

val SOLICITACAO_PROCEDIMENTO_ID = IdSolicitacaoProcedimento(UUID.fromString("4a2fadf0-4f59-4a2e-b310-a2b264e9e88a"))
val SOLICITACAO_PROCEDIMENTO_STATUS = StatusSolicitacaoProcedimento.ABERTO
val SOLICITACAO_PROCEDIMENTO_DATA_CRIACAO = LocalDate.now()
val SOLICITACAO_PROCEDIMENTO_DATA_LIBERACAO_REJEICAO = LocalDate.now()
const val SOLICITACAO_PROCEDIMENTO_DESCRICAO_REJEICAO = "Descrição rejeição"

