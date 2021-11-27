package br.unipar.plano.interfaces.rest.solicitacaoprocedimento.factories

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.IdCarteirinha
import br.unipar.plano.domain.contrato.model.Contrato
import br.unipar.plano.domain.contrato.model.IdContrato
import br.unipar.plano.domain.prestador.model.IdPrestador
import br.unipar.plano.domain.prestador.model.Prestador
import br.unipar.plano.domain.procedimento.model.Especialidade
import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Servico
import br.unipar.plano.domain.procedimento.model.StatusProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoDTO
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoDTO
import java.math.BigDecimal
import java.time.LocalDate


fun SolicitacaoProcedimentoTestHelper(
    procedimento: ProcedimentoDTO = ProcedimentoTestHelper(),
    statusSolicitacao: StatusSolicitacaoProcedimento = StatusSolicitacaoProcedimento.ABERTO,
    dataCriacao: LocalDate = LocalDate.now(),
    dataLiberacaoRejeicao: LocalDate = LocalDate.now(),
    descricaoRejeicao: String = " "

) = SolicitacaoProcedimentoDTO(
    procedimento = procedimento,
    statusSolicitacao = statusSolicitacao,
    dataCriacao = dataCriacao,
    dataLiberacaoRejeicao = dataLiberacaoRejeicao,
    descricaoRejeicao = descricaoRejeicao
)

fun ProcedimentoTestHelper(
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

) = ProcedimentoDTO(
    id = IdProcedimento(),
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