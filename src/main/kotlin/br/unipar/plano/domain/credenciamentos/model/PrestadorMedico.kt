package br.unipar.plano.domain.credenciamentos.model

import jdk.dynalink.beans.StaticClass
import javax.persistence.*

@Entity
class PrestadorMedico(

    @Id
    @Column(nullable = false)
    val id: Long,

    @Column(nullable = false)
    val nome: String,

    @Enumerated(EnumType.STRING)
    val status: Status,

    @Column(nullable = false)
    val crm: String,

    @Column(nullable = false)
    val especialidade: String

) {

    fun with(
        id: Long = this.id,
        nome: String = this.nome,
        status: Status = this.status,
        crm: String = this.crm,
        especialidade: String = this.especialidade
    ) = copy(
        id =id,
        nome = nome,
        status = status,
        crm = crm,
        especialidade = especialidade
    )

    private fun copy(
        id: Long = this.id,
        nome: String = this.nome,
        status: Status = this.status,
        crm: String = this.crm,
        especialidade: String = this.especialidade
    ) = PrestadorMedico(
        id =id,
        nome = nome,
        status = status,
        crm = crm,
        especialidade = especialidade
    )

}