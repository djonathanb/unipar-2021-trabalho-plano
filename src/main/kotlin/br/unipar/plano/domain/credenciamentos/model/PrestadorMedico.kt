package br.unipar.plano.domain.credenciamentos.model

import javax.persistence.*
import javax.validation.constraints.Pattern

@Entity
class PrestadorMedico(

    @field:EmbeddedId
    val id: IdPrestadorMedico,

    @Column(nullable = false)
    val nome: String,

    @Enumerated(EnumType.STRING)
    val status: Status,

    @Column(nullable = false)
    val crm: String,

    @Column(nullable = true)
    @OneToMany(cascade = [CascadeType.ALL])
    val especialidade: List<Especialidade>?

) {

    fun with(
        id: IdPrestadorMedico = this.id,
        nome: String = this.nome,
        status: Status = this.status,
        crm: String = this.crm,
        especialidade: List<Especialidade>? = this.especialidade
    ) = copy(
        id = id,
        nome = nome,
        status = status,
        crm = crm,
        especialidade = especialidade
    )

    private fun copy(
        id: IdPrestadorMedico = this.id,
        nome: String = this.nome,
        status: Status = this.status,
        crm: String = this.crm,
        especialidade: List<Especialidade>? = this.especialidade
    ) = PrestadorMedico(
        id = id,
        nome = nome,
        status = status,
        crm = crm,
        especialidade = especialidade
    )

}