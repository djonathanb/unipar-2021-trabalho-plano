package br.unipar.plano.interfaces.rest.contratos

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.model.Plano
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.pessoas.model.Pessoa
import java.time.LocalDate
import javax.validation.constraints.NotBlank

data class ContratoSummaryDTO(
    val id: IdContrato,
    val idTitular: Pessoa,
    val idPlano: Plano,
    val dataContratacao: LocalDate,
    val dataContratoFinal : LocalDate
) {

    companion object {

        fun toDTO(contrato: Contrato) = ContratoSummaryDTO(
            id = contrato.id,
            idTitular = contrato.titular,
            idPlano = contrato.plano,
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
            id = contrato.id,
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
    val idPlano: Plano,

    @NotBlank(message = "ID Pessoa não informado")
    val idTitular: Pessoa

) {

    fun toModel(id: IdContrato) = Contrato(
        id = id,
        dataContratacao = this.dataContratacao,
        dataContratoFinal = this.dataContratoFinal,
        plano = this.idPlano,
        titular = this.idTitular
    )

    companion object {

        fun toDTO(contrato: Contrato) = ContratoDTO(
            dataContratacao = contrato.dataContratacao,
            dataContratoFinal = contrato.dataContratoFinal,
            idPlano = contrato.plano,
            idTitular = contrato.titular
        )
    }

}