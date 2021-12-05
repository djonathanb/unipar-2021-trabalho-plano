package br.unipar.plano.domain.credenciamentos.model.prestadorMedico

import javax.persistence.*

enum class StatusMedico {
    CRIADA, CREDENCIADA, DESCREDENCIADA, ATIVO, INATIVO
}

@Entity
class PrestadorMedico(

    @field:EmbeddedId
    val idPrestadorMedico: IdPrestadorMedico,

    @Column(nullable = false)
    val nome: String,

    @Enumerated(EnumType.STRING)
    val status: StatusMedico = StatusMedico.CRIADA,

    @Column(unique = true)
    val crm: String,

    @OneToMany(cascade = [CascadeType.ALL])
    val especialidades: List<Especialidade>

) {

    fun credencia(): PrestadorMedico {
        if (status == StatusMedico.CREDENCIADA) {
            throw IllegalStateException("Não é possível credenciar um médico com status $status")
        }
        return copy(status = StatusMedico.CREDENCIADA)
    }

    fun descredencia(): PrestadorMedico {
        if (status != StatusMedico.CREDENCIADA) {
            throw IllegalStateException("Não é possível descredenciar um médico com status $status")
        }
        return copy(status = StatusMedico.DESCREDENCIADA)
    }

    fun with(
        id: IdPrestadorMedico = this.idPrestadorMedico,
        nome: String = this.nome,
        crm: String = this.crm,
        especialidades: List<Especialidade> = this.especialidades
    ) = copy(
       id = id,
       nome = nome,
       crm = crm,
       especialidades = especialidades
    )

    private fun copy(
        id: IdPrestadorMedico = this.idPrestadorMedico,
        nome: String = this.nome,
        crm: String = this.crm,
        especialidades: List<Especialidade> = this.especialidades,
        status: StatusMedico = this.status
    ) = PrestadorMedico(
        idPrestadorMedico = id,
        nome = nome,
        crm = crm,
        especialidades = especialidades,
        status = status
    )

}