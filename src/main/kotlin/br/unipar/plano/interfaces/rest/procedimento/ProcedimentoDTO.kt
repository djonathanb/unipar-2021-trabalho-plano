package br.unipar.plano.interfaces.rest.procedimento

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.contrato.model.Contrato
import br.unipar.plano.domain.prestador.model.Prestador
import br.unipar.plano.domain.procedimento.model.*
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProcedimentoSummaryDTO(
    val id: IdProcedimento,
    val dataEmissao: LocalDate,
    val valor: BigDecimal,
    var dataCancelamento: LocalDate,
    var dataProcedimento: LocalDate,
    var contrato: Contrato,
    var carteirinha: Carteirinha,
    var prestador: Prestador,
    var status: StatusProcedimento,
    var especialidade: Especialidade,
    var servico: Servico

) {

    companion object {
        fun toDTO(procedimento: Procedimento) = ProcedimentoSummaryDTO(
            id               = procedimento.id,
            dataEmissao      = procedimento.dataEmissao,
            valor            = procedimento.valor,
            dataCancelamento = procedimento.dataCancelamento,
            dataProcedimento = procedimento.dataProcedimento,
            contrato         = procedimento.contrato,
            carteirinha      = procedimento.carteirinha,
            prestador        = procedimento.prestador,
            status           = procedimento.status,
            especialidade    = procedimento.especialidade,
            servico          = procedimento.servico
        )
    }
}

data class ProcedimentoDetailsDTO(
    val id: IdProcedimento,
    val status: StatusProcedimento,
    val procedimentoData: ProcedimentoDTO
) {

    companion object {
        fun toDTO(procedimento: Procedimento) = ProcedimentoDetailsDTO(
            id = procedimento.id,
            status = procedimento.status,
            procedimentoData = ProcedimentoDTO.toDTO(procedimento)
        )
    }
}

data class ProcedimentoDTO(

    @field:NotBlank(message = "A Data de emissão deve ser informada")
    val dataEmissao: LocalDate,

    @field:NotBlank(message = "O valor deve ser informado")
    val valor: BigDecimal,

    @field:NotNull
    val dataCancelamento: LocalDate,

    @field:NotNull
    val dataProcedimento: LocalDate,

    @field:NotNull
    val carteirinha: Carteirinha,

    @field:NotNull
    val contrato: Contrato,

    @field:NotNull
    val prestador: Prestador,

    @field:NotNull
    val status: StatusProcedimento,

    @field:NotNull
    val especialidade: Especialidade,

    @field:NotNull
    val servico: Servico
) {

    fun toModel(id: IdProcedimento) = Procedimento(
        id               = id,
        dataEmissao      = this.dataEmissao,
        valor            = this.valor,
        dataCancelamento = this.dataCancelamento,
        dataProcedimento = this.dataProcedimento,
        carteirinha      = this.carteirinha,
        contrato         = this.contrato,
        prestador        = this.prestador,
        status           = this.status,
        especialidade    = this.especialidade,
        servico          = this.servico
    )

    companion object {

        fun toDTO(procedimento: Procedimento) = ProcedimentoDTO(
            dataEmissao      = procedimento.dataEmissao,
            valor            = procedimento.valor,
            dataCancelamento = procedimento.dataCancelamento,
            dataProcedimento = procedimento.dataProcedimento,
            carteirinha      = procedimento.carteirinha,
            contrato         = procedimento.contrato,
            prestador        = procedimento.prestador,
            status           = procedimento.status,
            especialidade    = procedimento.especialidade,
            servico          = procedimento.servico
        )
    }
}
