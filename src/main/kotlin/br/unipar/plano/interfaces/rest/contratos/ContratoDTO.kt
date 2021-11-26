package br.unipar.plano.interfaces.rest.contratos

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.model.StatusContrato
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotBlank

data class ContratoSummaryDTO(
    val id: IdContrato,
    val idTitular: UUID,
    val idPlano: UUID,
    val dataContratacao: LocalDate,
    val dataContratoFinal : LocalDate
) {

    companion object {

        fun toDTO(contrato: Contrato) = ContratoSummaryDTO(
            id = contrato.idContrato,
            idTitular = contrato.idTitular,
            idPlano = contrato.idPlano,
            dataContratacao = contrato.dataContratacao,
            dataContratoFinal = contrato.dataContratoFinal
        )

    }

}

data class ContratoDetailsDTO(
    val id: IdContrato,
    val status: StatusContrato,
    val contratoData: ContratoDTO
) {

    companion object {

        fun toDTO(contrato: Contrato) = ContratoDetailsDTO(
            id = contrato.idContrato,
            status = contrato.status,
            contratoData = ContratoDTO.toDTO(contrato)
        )

    }

}

data class ContratoDTO(


    @NotBlank(message = "Data de contratação não informada")
    val dataContratacao: LocalDate,

    @NotBlank(message = "Data de termino de contrato não informada")
    val dataContratoFinal: LocalDate,

    @NotBlank(message = "ID Plano não informado")
    val idPlano: UUID,

    @NotBlank(message = "ID Pessoa não informado")
    val idTitular: UUID

) {

    fun toModel(id: IdContrato) = Contrato(
        idContrato = id,
        dataContratacao = this.dataContratacao,
        dataContratoFinal = this.dataContratoFinal,
        idPlano = this.idPlano,
        idTitular = this.idTitular
    )

    companion object {

        fun toDTO(contrato: Contrato) = ContratoDTO(
            dataContratacao = contrato.dataContratacao,
            dataContratoFinal = contrato.dataContratoFinal,
            idPlano = contrato.idPlano,
            idTitular = contrato.idTitular
        )
    }

}