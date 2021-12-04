package br.unipar.plano.domain.procedimento.model.factories

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.Endereco
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.domain.contrato.model.Contrato
import br.unipar.plano.domain.prestador.model.Prestador
import br.unipar.plano.domain.procedimento.model.*
import java.math.BigDecimal
import java.time.LocalDate

fun idProcedimento(static: Boolean = true) = if (static) {
    PROCEDIMENTO_CO_ID
} else {
    IdProcedimento()
}

fun procedimento(
    id: IdProcedimento = idProcedimento(),
    dataEmissao: LocalDate = LocalDate.now(),
    valor: BigDecimal = PROCEDIMENTO_CO_VALOR,
    status: StatusProcedimento = PROCEDIMENTO_CO_STATUS,
    dataProcedimento: LocalDate = PROCEDIMENTO_CO_DATAPROC,
    servico: Servico = PROCEDIMENTO_CO_SERVICO,
    especialidade: Especialidade = PROCEDIMENTO_CO_EPECIALIDADE,
    dataCancelamento: LocalDate
) = Procedimento(
    id = id,
    dataEmissao = dataEmissao,
    valor = valor,
    status = status,
    dataProcedimento = dataProcedimento,
    servico = servico,
    especialidade = especialidade,
    dataCancelamento = dataCancelamento
)