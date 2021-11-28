package br.unipar.plano.domain.credenciamentos.model.prestadorMedico

import javax.persistence.*

enum class StatusMedico {
    CRIADA, CREDENCIADA, DESCREDENCIADA
}

@Entity
class PrestadorMedico(

    @field:EmbeddedId
    val id: IdPrestadorMedico,

    @Column(nullable = false)
    val nome: String,

    @Enumerated(EnumType.STRING)
    val status: StatusMedico = StatusMedico.CRIADA,

    @Column(unique = true)
    val crm: String,


    //@OneToMany(cascade = [CascadeType.ALL])
    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    val especialidades: Especialidade

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
        id: IdPrestadorMedico = this.id,
        nome: String = this.nome,
        crm: String = this.crm,
        especialidades: Especialidade = this.especialidades
    ) = copy(
       id = id,
       nome = nome,
       crm = crm,
       especialidades = especialidades
    )

    private fun copy(
        id: IdPrestadorMedico = this.id,
        nome: String = this.nome,
        crm: String = this.crm,
        especialidades: Especialidade = this.especialidades,
        status: StatusMedico = this.status
    ) = PrestadorMedico(
        id = id,
        nome = nome,
        crm = crm,
        especialidades = especialidades,
        status = status
    )

}