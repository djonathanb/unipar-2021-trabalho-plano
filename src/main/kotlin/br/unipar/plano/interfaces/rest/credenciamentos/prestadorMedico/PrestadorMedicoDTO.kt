package br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.Especialidade
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.StatusMedico
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

private const val MIN_NAME_SIZE = 10
private const val MAX_NAME_SIZE = 120

data class PrestadorMedicoSummaryDTO(
    val id: IdPrestadorMedico,
    val nome: String,
    val crm: String,
    val especialidades: List<EspecialidadeSummaryDTO>
) {
    companion object {

        fun toDTO(prestadorMedico: PrestadorMedico) = PrestadorMedicoSummaryDTO(
            id = prestadorMedico.idPrestadorMedico,
            nome = prestadorMedico.nome,
            crm = prestadorMedico.crm,
            especialidades = prestadorMedico.especialidades.map { especialidade -> EspecialidadeSummaryDTO(nomeEspecialidade = especialidade.nomeEspecialidade) }
            )
    }
}

data class PrestadorMedicoDetailsDTO(
    val id: IdPrestadorMedico,
    val status: StatusMedico,
    val prestMedData: PrestMedDTO
){
    companion object {
        fun toDTO(prestadorMedico: PrestadorMedico) = PrestadorMedicoDetailsDTO(
            id = prestadorMedico.idPrestadorMedico,
            status = prestadorMedico.status,
            prestMedData = PrestMedDTO.toDTO(prestadorMedico)
        )
    }
}

data class PrestMedDTO(

    @field:NotBlank(message = "O nome deve ser informado")
    val nome: String,

    @field:NotBlank(message = "O CRM deve ser informado")
    @field:Pattern(regexp = "^[0-9]{5}+[/]+[A-Z]{2}$")
    val crm: String,

    @field:NotNull
    val especialidades: List<EspecialidadeDTO>

) {

    fun toModel(id: IdPrestadorMedico) = PrestadorMedico(
        idPrestadorMedico = id,
        nome = this.nome,
        crm = this.crm,
        especialidades = especialidades.map {especialidade -> Especialidade(id = IdPrestadorMedico() ,nomeEspecialidade = especialidade.nomeEspecialidade) }

    )

    companion object {

        fun toDTO(prestadorMedico: PrestadorMedico) = PrestMedDTO(
            nome = prestadorMedico.nome,
            crm  = prestadorMedico.crm,
            especialidades = prestadorMedico.especialidades.map{especialidade -> EspecialidadeDTO(nomeEspecialidade = especialidade.nomeEspecialidade) }
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


data class EspecialidadeSummaryDTO(
    val nomeEspecialidade: String
) {
    companion object {

        fun toDTO(especialidade: Especialidade) = EspecialidadeSummaryDTO(
            nomeEspecialidade = especialidade.nomeEspecialidade
        )
    }
}
