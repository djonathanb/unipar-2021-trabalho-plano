package br.unipar.plano.interfaces.rest.centrais

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

private const val MIN_NAME_SIZE = 10
private const val MAX_NAME_SIZE = 120

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

)

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
