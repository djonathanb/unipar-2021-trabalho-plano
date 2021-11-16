package br.unipar.plano.domain.credenciamentos.model

import javax.persistence.*

@Entity
class PrestadorClinicaHospital(

    @Id
    @Column(nullable = false)
    val id: Long,

    @Column(nullable = false)
    val nome: String,

    @Enumerated(EnumType.STRING)
    val status: Status,

    @Column(nullable = false)
    val responsavel: PrestadorMedico,

    @Column(nullable = false)
    val servico: String
){

    fun with(
        id: Long = this.id,
        nome: String = this.nome,
        status: Status = this.status,
        responsavel: PrestadorMedico = this.responsavel,
        servico: String = this.servico
    ) = copy(
        id =id,
        nome = nome,
        status = status,
        responsavel = responsavel,
        servico = servico
    )

    private fun copy(
        id: Long = this.id,
        nome: String = this.nome,
        status: Status = this.status,
        responsavel: PrestadorMedico = this.responsavel,
        servico: String = this.servico
    ) = PrestadorClinicaHospital(
        id =id,
        nome = nome,
        status = status,
        responsavel = responsavel,
        servico = servico
    )
}