package br.unipar.plano.interfaces.rest.centrais

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.Endereco
import br.unipar.plano.domain.centrais.model.IdCentral
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
    val centralData: CentralDTO
) {

    companion object {

        fun toDTO(central: Central) = CentralDetailsDTO(
            id = central.id,
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

    fun toModel(id: IdCentral, centralDTO: CentralDTO) = Central(
        id = id,
        nome = centralDTO.nome,
        cnpj = centralDTO.cnpj,
        endereco = Endereco(
            idCentral = id,
            cidade = centralDTO.endereco.cidade,
            cep = centralDTO.endereco.cep,
            bairro = centralDTO.endereco.bairro,
            logradouro = centralDTO.endereco.logradouro,
            numero = centralDTO.endereco.numero,
            complemento = centralDTO.endereco.complemento
        )
    )

    companion object {

        fun toDTO(central: Central) = CentralDTO(
            nome = central.nome,
            cnpj = central.cnpj,
            endereco = EnderecoDTO(
                cidade = central.endereco.cidade,
                cep = central.endereco.cep,
                bairro = central.endereco.bairro,
                logradouro = central.endereco.logradouro,
                numero = central.endereco.numero,
                complemento = central.endereco.complemento
            )
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
)
