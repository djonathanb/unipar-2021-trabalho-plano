package br.unipar.plano.domain.solicitacaoprocedimento.model.factories

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.IdCarteirinha
import br.unipar.plano.domain.contrato.model.Contrato
import br.unipar.plano.domain.contrato.model.IdContrato
import br.unipar.plano.domain.prestador.model.IdPrestador
import br.unipar.plano.domain.prestador.model.Prestador
import br.unipar.plano.domain.procedimento.model.*
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import java.math.BigDecimal
import java.time.LocalDate

fun idSolicitacaoProcedimento(static: Boolean = true) = if (static) {
    SOLICITACAO_PROCEDIMENTO_ID
} else {
    IdSolicitacaoProcedimento()
}

fun solicitacaoProcedimento(
    id: IdSolicitacaoProcedimento = idSolicitacaoProcedimento(),
    procedimento: Procedimento = procedimento(),
    statusSolicitacao: StatusSolicitacaoProcedimento = SOLICITACAO_PROCEDIMENTO_STATUS,
    dataCriacao: LocalDate = SOLICITACAO_PROCEDIMENTO_DATA_CRIACAO,
    dataLiberacaoRejeicao: LocalDate? = SOLICITACAO_PROCEDIMENTO_DATA_LIBERACAO_REJEICAO,
    descricaoRejeicao: String? = SOLICITACAO_PROCEDIMENTO_DESCRICAO_REJEICAO
) = SolicitacaoProcedimento(
    id = id,
    procedimento = procedimento,
    statusSolicitacao = statusSolicitacao,
    dataCriacao = dataCriacao,
    dataLiberacaoRejeicao = dataLiberacaoRejeicao,
    descricaoRejeicao = descricaoRejeicao
)

fun procedimento(
    id: IdProcedimento = IdProcedimento(),
    dataEmissao: LocalDate = LocalDate.now(),
    valor: BigDecimal = BigDecimal.TEN,
    dataCancelamento: LocalDate = LocalDate.now(),
    dataProcedimento: LocalDate = LocalDate.now(),
    carteirinha: Carteirinha = Carteirinha(id = IdCarteirinha()),
    contrato: Contrato = Contrato(id = IdContrato()),
    prestador: Prestador = Prestador(id = IdPrestador()),
    status: StatusProcedimento = StatusProcedimento.PENDENTE,
    especialidade: Especialidade = Especialidade.ACUPUNTURA,
    servico: Servico = Servico.ADMINISTRACAO_SAUDE
) = Procedimento(
    id = id,
    dataEmissao = dataEmissao,
    valor = valor,
    dataCancelamento = dataCancelamento,
    dataProcedimento = dataProcedimento,
    carteirinha = carteirinha,
    contrato = contrato,
    prestador = prestador,
    status = status,
    especialidade = especialidade,
    servico = servico
)
