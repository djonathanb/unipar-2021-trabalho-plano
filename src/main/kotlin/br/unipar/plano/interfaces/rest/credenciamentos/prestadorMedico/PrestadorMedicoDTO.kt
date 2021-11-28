package br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico

import br.unipar.plano.domain.centrais.model.Endereco
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.credenciamentos.model.outros.Especialidade
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.StatusMedico
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

private const val MIN_NAME_SIZE = 10
private const val MAX_NAME_SIZE = 120

////////////////////////////////////////////////////////////
//PrestadorMedicoSummaryDTO
data class PrestadorMedicoSummaryDTO(
    val id: IdPrestadorMedico,
    val nome: String,
    val crm: String
) {
    companion object {

        fun toDTO(prestadorMedico: PrestadorMedico) = PrestadorMedicoSummaryDTO(
            id   = prestadorMedico.id,
            nome = prestadorMedico.nome,
            crm  = prestadorMedico.crm
            )
    }
}
////////////////////////////////////////////////////////////
//PrestadorMedicoDetailsDTO
data class PrestadorMedicoDetailsDTO(
    val id: IdPrestadorMedico,
    val status: StatusMedico,
    val prestMedData: PrestMedDTO
){

    companion object {

        fun toDTO(prestadorMedico: PrestadorMedico) = PrestadorMedicoDetailsDTO(
            id = prestadorMedico.id,
            status = prestadorMedico.status,
            prestMedData = PrestMedDTO.toDTO(prestadorMedico)
        )

    }

}
////////////////////////////////////////////////////////////
//PrestMedDTO
data class PrestMedDTO(

    @field:NotBlank(message = "O nome deve ser informado")
    val nome: String,

    @field:NotNull
    val status: StatusMedico,

    @field:NotBlank(message = "O CRM deve ser informado")
    @field:Pattern(regexp = "^[0-9]{5}+[/]+[A-Z]{2}$")
    val crm: String,

    @field:NotNull
    val especialidades: EspecialidadeDTO

) {

    fun toModel(id: IdPrestadorMedico) = PrestadorMedico(
        id = id,
        nome = this.nome,
        crm = this.crm,
        status = this.status,
        especialidades = especialidades.toModel(idPrestadorMedico = id)

    )

    companion object {

        fun toDTO(prestadorMedico: PrestadorMedico) = PrestMedDTO(
            nome = prestadorMedico.nome,
            crm  = prestadorMedico.crm,
            status = prestadorMedico.status,
            especialidades = EspecialidadeDTO.toDTO(prestadorMedico.especialidades)
        )

    }
}

data class EspecialidadeDTO(

    @field:NotNull
    val nomeEspecialidade: String
) {

    fun toModel(idPrestadorMedico: IdPrestadorMedico) = Especialidade(
        id = idPrestadorMedico,
        nomeEspecialidade = this.nomeEspecialidade

    )

    companion object {

        fun toDTO(especialidade: Especialidade) = EspecialidadeDTO(
            nomeEspecialidade = especialidade.nomeEspecialidade
        )

    }
}