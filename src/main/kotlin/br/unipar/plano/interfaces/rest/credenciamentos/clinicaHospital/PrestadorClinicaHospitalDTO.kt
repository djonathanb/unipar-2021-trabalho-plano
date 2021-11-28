package br.unipar.plano.interfaces.rest.credenciamentos.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.status.Status
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PrestClinHospSummaryDTO(
    val id: IdPrestadorClinicaHospital,
    val nome: String,
    val status: Status,
    val cnpj: String,
    var servicos: List<ServicoSummaryDTO>
)

data class PrestClinHospDetailsDTO(
    val id: IdPrestadorClinicaHospital,
    val prestClinHospData: PrestClinHospDTO
)

data class PrestClinHospDTO(

    @field:NotBlank(message = "O nome deve ser informado")
    val nome: String,

    @field:NotBlank(message = "O nome deve ser informado")
    val cnpj: String,

   // @field:NotNull
   // var responsavel: PrestMedDTO,

    @field:NotNull
    var status: Status,

    @field:NotNull
    var servicos: List<ServicoDTO>
)

data class ServicoSummaryDTO(
    val servico: String
)

data class ServicoDTO(
    @field:NotBlank(message = "O servico deve ser informado")
    val servico: String
)
