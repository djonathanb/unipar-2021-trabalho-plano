package br.unipar.plano.interfaces.rest.cobrancas

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotNull


data class CobrancaSummaryDTO(
    val id: IdCobranca,
    val valorContrato: BigDecimal,
    val dataEmissao: LocalDate,
    val dataVencimento: LocalDate,
    var valorTotal: BigDecimal?,
    val contrato: ContratoDTO
) {

    companion object {

        fun toDTO(cobranca: Cobranca) = CobrancaSummaryDTO(
            id = cobranca.id,
            valorContrato = cobranca.valorContrato,
            dataEmissao = cobranca.dataEmissao,
            dataVencimento = cobranca.dataVencimento,
            valorTotal = cobranca.valorTotal,
            contrato = ContratoDTO.toDTO(cobranca.contrato)
        )

    }

}

data class CobrancaDetailsDTO(
    val id: IdCobranca,
    val status: StatusCobranca,
    val cobrancaData: CobrancaDTO
) {

    companion object {

        fun toDTO(cobranca: Cobranca) = CobrancaDetailsDTO(
            id = cobranca.id,
            status = cobranca.status,
            cobrancaData = CobrancaDTO.toDTO(cobranca)
        )

    }

}

data class CobrancaDTO(

    val valorContrato: BigDecimal,
    val valorAdicionalConsulta: BigDecimal,
    val valorAdicionalCirurgia: BigDecimal,
    val valorAdicionalIdade: BigDecimal,
    val dataEmissao: LocalDate,
    var dataCancelamento: LocalDate?,
    val dataVencimento: LocalDate,
    var valorTotal: BigDecimal?,
    val contrato: ContratoDTO

) {


    companion object {

        fun toDTO(cobranca: Cobranca) = CobrancaDTO(
            valorAdicionalConsulta = cobranca.valorAdicionalConsulta,
            valorAdicionalCirurgia = cobranca.valorAdicionalCirurgia,
            valorAdicionalIdade = cobranca.valorAdicionalIdade,
            dataEmissao = cobranca.dataEmissao,
            dataCancelamento = cobranca.dataCancelamento,
            dataVencimento = cobranca.dataVencimento,
            valorTotal = cobranca.valorTotal,
            valorContrato = cobranca.valorContrato,
            contrato = ContratoDTO.toDTO(contrato = cobranca.contrato)
        )
    }

}

data class ContratoDTO(

    @field:NotNull
    val id: UUID
) {

    companion object {

        fun toDTO(contrato: Contrato) = ContratoDTO(
            id = contrato.id
        )
    }

}