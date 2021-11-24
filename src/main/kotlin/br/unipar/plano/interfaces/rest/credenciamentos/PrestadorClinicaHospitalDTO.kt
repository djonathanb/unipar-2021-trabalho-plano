package br.unipar.plano.interfaces.rest.credenciamentos

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.Status
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/*
private const val MIN_NAME_SIZE = 10
private const val MAX_NAME_SIZE = 120

data class PrestClinHospSummaryDTO(
    val id: IdPrestadorClinicaHospital,
    val nome: String,
    val status: Status,
    val cnpj: String,
    var responsavel: String
)

data class PrestClinHospDetailsDTO(
    val id: IdPrestadorClinicaHospital,
    val prestClinHospData: PrestClinHospDTO
)

data class PrestClinHospDTO(

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
    var responsavel: PrestMedDTO,

    @field:NotNull
    var status: Status,

    @field:NotNull
    var servico: ServicoDTO
)

data class ServicoDTO(

    @field:NotNull
    var servico: String

)
 */