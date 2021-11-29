package br.unipar.plano.interfaces.rest.credenciamentos.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.Servico
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.StatusClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.Especialidade
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.status.Status
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.EspecialidadeDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestMedDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestadorMedicoDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.PrestadorMedicoSummaryDTO
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PrestClinHospSummaryDTO(
    val id: IdPrestadorClinicaHospital,
    val nome: String,
    val cnpj: String
){
    companion object {
        fun toDTO(prestadorClinicaHospital: PrestadorClinicaHospital) = PrestClinHospSummaryDTO(
            id = prestadorClinicaHospital.id,
            nome = prestadorClinicaHospital.nome,
            cnpj = prestadorClinicaHospital.cnpj
        )
    }
}

data class PrestClinHospDetailsDTO(
    val id: IdPrestadorClinicaHospital,
    val status: StatusClinicaHospital,
    val prestClinHospData: PrestClinHospDTO
){
    companion object {
        fun toDTO(prestadorClinicaHospital: PrestadorClinicaHospital) = PrestClinHospDetailsDTO(
            id = prestadorClinicaHospital.id,
            status = prestadorClinicaHospital.status,
            prestClinHospData = PrestClinHospDTO.toDTO(prestadorClinicaHospital)
        )
    }
}

data class PrestClinHospDTO(

    @field:NotBlank(message = "O nome deve ser informado")
    val nome: String,

    @field:NotBlank(message = "O CNPJ deve ser informado")
    val cnpj: String,

   // @field:NotNull
   // var responsavel: PrestMedDTO,

    @field:NotNull
    var status: StatusClinicaHospital,

    @field:NotNull
    var servico: ServicoDTO
){

    fun toModel(id: IdPrestadorClinicaHospital) = PrestadorClinicaHospital(
        id = id,
        nome = this.nome,
        cnpj = this.cnpj,
        status = this.status,
        servico = servico.toModel(idPrestadorClinicaHospital = id)

    )
    companion object {
        fun toDTO(prestadorClinicaHospital: PrestadorClinicaHospital) = PrestClinHospDTO(
            nome = prestadorClinicaHospital.nome,
            cnpj = prestadorClinicaHospital.cnpj,
            status = prestadorClinicaHospital.status,
            servico = ServicoDTO.toDTO(prestadorClinicaHospital.servico)
        )
    }
}

data class ServicoSummaryDTO(
    val servico: String
)

data class ServicoDTO(
    @field:NotBlank(message = "O servico deve ser informado")
    val servico: String
){
    fun toModel(idPrestadorClinicaHospital: IdPrestadorClinicaHospital) = Servico(
        id = idPrestadorClinicaHospital,
        servico = this.servico
    )
    companion object {
        fun toDTO(servico: Servico) = ServicoDTO(
            servico = servico.servico
        )
    }
}
