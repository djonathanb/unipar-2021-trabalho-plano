package br.unipar.plano.interfaces.rest.credenciamentos

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.Status
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

private const val MIN_CRM_SIZE = 0
private const val MAX_CRM_SIZE = 10

data class PrestadorMedicoSummaryDTO(
    val id: IdPrestadorMedico,
    val nome: String,
    val status: Status,
    val crm: String,
    val especialidade: String

    )


data class PrestadorMedicoDetailsDTO(
    val id: IdPrestadorMedico,
    val prestMedData: PrestMedDTO
)

data class PrestMedDTO(

    @field:NotBlank(message = "O nome deve ser informado")
    val nome: String,

    @field:NotNull
    val status: Status,

    @field:NotBlank(message = "O CRM deve ser informado")
    @field:Size(
        min = MIN_CRM_SIZE,
        max = MAX_CRM_SIZE,
        message = "O CRM deve ter entre $MIN_CRM_SIZE e $MAX_CRM_SIZE caracteres"
    )
    val crm: String,

    @field:NotBlank(message = "A especialidade deve ser informada")
    val especialidade: EspecialidadeDTO

)

data class EspecialidadeDTO(
    @field:NotNull
    val nomeEspecialidade: String
)
