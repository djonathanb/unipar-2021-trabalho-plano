package br.unipar.plano.interfaces.rest.credenciamentos

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.Status
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class PrestadorMedicoSummaryDTO(
    val id: IdPrestadorMedico,
    val nome: String,
    val crm: String,
    val status: Status,
    val nomeEspecialidade: String
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
    @field:Pattern(regexp = "^[0-9]{5}+[/]+[A-Z]{2}$")
    val crm: String,

    @field:NotNull
    val especialidade: EspecialidadeDTO

)

data class EspecialidadeDTO(
    @field:NotNull
    val nomeEspecialidade: String
)
