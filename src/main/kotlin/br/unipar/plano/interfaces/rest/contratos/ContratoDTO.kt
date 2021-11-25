package br.unipar.plano.interfaces.rest.contratos

import br.unipar.plano.domain.centrais.model.Contrato
import br.unipar.plano.domain.centrais.model.IdContrato
import br.unipar.plano.domain.centrais.model.StatusContrato
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class ContratoSummaryDTO(
    val id: IdContrato,
    val idTitular: Int,
    val idPlano: Int,
    val dataContratacao: Date,
    val dataContratoFinal : Date
) {

    companion object {

        fun toDTO(contrato: Contrato) = ContratoSummaryDTO(
            id = contrato.idContrato,
            idTitular = contrato.idTitular,
            idPlano = contrato.idPlano,
            dataContratacao = contrato.dataContratacao,
            dataContratoFinal = contrato.dataContratofinal
        )

    }

}

data class CentralDetailsDTO(
    val id: IdContrato,
    val status: StatusContrato,
    val centralData: ContratoDTO
) {

    companion object {

        fun toDTO(contrato: Contrato) = ContratoDetailsDTO(
            id = contrato.idContrato,
            status = contrato.status,
            centralData = ContratoDTO.toDTO(contrato)
        )

    }

}

data class ContratoDTO(

    @field:NotBlank(message = "O nome deve ser informado")
    @field:Size(
        min = MIN_NAME_SIZE,
        max = MAX_NAME_SIZE,
        message = "O nome deve ter entre $MIN_NAME_SIZE e $MAX_NAME_SIZE caracteres"
    )
    val nome: String,

    @field:NotBlank(message = "O nome deve ser informado")
    val cnpj: String,

    @field:NotNull
    val endereco: EnderecoDTO

) {

    fun toModel(id: IdCentral) = Central(
        id = id,
        nome = this.nome,
        cnpj = this.cnpj,
        endereco = endereco.toModel(idCentral = id)
    )

    companion object {

        fun toDTO(central: Central) = CentralDTO(
            nome = central.nome,
            cnpj = central.cnpj,
            endereco = EnderecoDTO.toDTO(central.endereco)
        )
    }

}