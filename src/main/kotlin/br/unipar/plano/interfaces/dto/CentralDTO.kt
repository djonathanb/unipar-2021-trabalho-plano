package br.unipar.plano.interfaces.dto

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.Endereco
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.model.StatusCentral
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

private const val MIN_NAME_SIZE = 10
private const val MAX_NAME_SIZE = 120

data class CentralSummaryDTO(
    val id: IdCentral,
    val nome: String,
    val cidade: Int
) {

    companion object {

        fun toDTO(central: Central) = CentralSummaryDTO(
            id = central.id,
            nome = central.nome,
            cidade = central.endereco.cidade,
        )

    }

}

data class CentralDetailsDTO(
        val id: IdCentral,
        val status: StatusCentral,
        val centralData: CentralDTO
) {

    companion object {

        fun toDTO(central: Central) = CentralDetailsDTO(
            id = central.id,
            status = central.status,
            centralData = CentralDTO.toDTO(central)
        )

    }

}

data class CentralDTO(

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

data class EnderecoDTO(

    @field:NotNull
    @field:Size(min = 1010000)
    val cidade: Int,

    val cep: String,
    val bairro: String,
    val logradouro: String,
    val numero: Int,
    val complemento: String
) {

    fun toModel(idCentral: IdCentral) = Endereco(
        idCentral = idCentral,
        cidade = this.cidade,
        cep = this.cep,
        bairro = this.bairro,
        logradouro = this.logradouro,
        numero = this.numero,
        complemento = this.complemento
    )

    companion object {

        fun toDTO(endereco: Endereco) = EnderecoDTO(
            cidade = endereco.cidade,
            cep = endereco.cep,
            bairro = endereco.bairro,
            logradouro = endereco.logradouro,
            numero = endereco.numero,
            complemento = endereco.complemento
        )

    }

}
