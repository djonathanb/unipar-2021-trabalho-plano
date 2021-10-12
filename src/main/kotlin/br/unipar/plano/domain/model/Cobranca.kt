package br.unipar.plano.domain.model

import br.unipar.plano.domain.valueobjects.StatusCobranca
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.util.*


class Cobranca(
    val id: UUID,
    val valorContrato: BigDecimal,
    val valorAdicionalConsulta: BigDecimal,
    val valorAdicionalCirurgia: BigDecimal,
    val valorAdicionalIdade: BigDecimal,
    var status: StatusCobranca = StatusCobranca.ABERTO,
    val dataEmissao: LocalDate,
    var dataCancelamento: LocalDate?,
    val dataVencimento: LocalDate,
    var valorTotal : BigDecimal?,
    val contrato: Contrato
) {
    init {
        valorTotal = valorContrato
            .add(valorAdicionalIdade)
            .add(valorAdicionalCirurgia
                .multiply(BigDecimal.valueOf(contrato.cirurgias.count().toDouble())))
            .add(valorAdicionalConsulta
                .multiply(BigDecimal.valueOf(contrato.procedimentos.count().toDouble())))
            .setScale(2, RoundingMode.HALF_UP)
    }

    fun cancelar() {
        if (status == StatusCobranca.ABERTO || status == StatusCobranca.PAGO) {
            dataCancelamento = LocalDate.now()
            status = StatusCobranca.CANCELADO
        }
    }
}