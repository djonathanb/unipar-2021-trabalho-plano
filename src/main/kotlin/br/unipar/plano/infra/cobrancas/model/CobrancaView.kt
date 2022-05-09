package br.unipar.plano.infra.cobrancas.model

import br.unipar.plano.domain.cobrancas.model.*
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Document(collection = "cobrancas")
data class CobrancaView(
    var id: UUID,
    val valorContrato: BigDecimal,
    val valorAdicionalConsulta: BigDecimal,
    val valorAdicionalCirurgia: BigDecimal,
    val valorAdicionalIdade: BigDecimal,
    var status: StatusCobranca = StatusCobranca.ABERTO,
    val dataEmissao: LocalDate,
    var dataCancelamento: LocalDate?,
    val dataVencimento: LocalDate,
    var valorTotal: BigDecimal?,
    val contrato: Contrato,
    val cirurgias: Collection<Cirurgia>,
    val procedimentos: Collection<Procedimento>,
)

fun CobrancaView.toCobranca(): Cobranca {
    return Cobranca(
        id = IdCobranca(id),
        valorContrato = valorContrato,
        valorAdicionalConsulta = valorAdicionalConsulta,
        valorAdicionalCirurgia = valorAdicionalCirurgia,
        valorAdicionalIdade = valorAdicionalIdade,
        status = status,
        dataEmissao = dataEmissao,
        dataCancelamento = dataCancelamento,
        dataVencimento = dataVencimento,
        valorTotal = valorTotal,
        contrato = contrato,
        cirurgias = cirurgias,
        procedimentos = procedimentos
    )
}