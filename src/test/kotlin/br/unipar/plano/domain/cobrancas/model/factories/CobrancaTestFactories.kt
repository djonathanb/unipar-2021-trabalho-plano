package br.unipar.plano.domain.cobrancas.model.factories

import br.unipar.plano.domain.cobrancas.model.*
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import java.math.BigDecimal
import java.time.LocalDate

fun idCobranca(static: Boolean = true) = if (static) {
    COBRANCA_ID
} else {
    IdCobranca()
}

fun cobranca(
    id: IdCobranca = COBRANCA_ID,
    valorContrato: BigDecimal = VALOR_CONTRATO,
    valorAdicionalConsulta: BigDecimal = VALOR_ADICIONAL_CONSULTA,
    valorAdicionalCirurgia: BigDecimal = VALOR_ADICIONAL_CIRURGIA,
    valorAdicionalIdade: BigDecimal = VALOR_ADICIONAL_IDADE,
    status: StatusCobranca = StatusCobranca.ABERTO,
    dataEmissao: LocalDate = DATA_EMISSAO_COBRANCA,
    dataCancelamento: LocalDate? = null,
    dataVencimento: LocalDate = DATA_VENCIMENTO_COBRANCA,
    valorTotal: BigDecimal? = null,
    contrato: Contrato = contrato(),
    procedimentos: Collection<Procedimento> = mutableListOf(
        Procedimento(id = PROCEDIMENTO_ID_1),
        Procedimento(id = PROCEDIMENTO_ID_2)
    ),
    cirurgias: Collection<Cirurgia> = mutableListOf(Cirurgia(id = CIRURGIA_ID_1), Cirurgia(id = CIRURGIA_ID_2)),
) = Cobranca(
    id,
    valorContrato,
    valorAdicionalConsulta,
    valorAdicionalCirurgia,
    valorAdicionalIdade,
    status,
    dataEmissao,
    dataCancelamento,
    dataVencimento,
    valorTotal,
    contrato,
    cirurgias,
    procedimentos
)

