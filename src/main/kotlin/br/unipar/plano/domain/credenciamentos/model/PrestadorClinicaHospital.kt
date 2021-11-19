package br.unipar.plano.domain.credenciamentos.model

import javax.persistence.*

/*
@Entity
class PrestadorClinicaHospital(

    @field:EmbeddedId
    val id: IdPrestadorClinicaHospital,

    @Column(nullable = false)
    val nome: String,

    @Enumerated(EnumType.STRING)
    val status: Status,

    @Column(nullable = false)
    val responsavel: PrestadorMedico,

    @Column(nullable = false)
    val servico: String
) {

    fun with(
        id: IdPrestadorClinicaHospital = this.id,
        nome: String = this.nome,
        status: Status = this.status,
        responsavel: PrestadorMedico = this.responsavel,
        servico: String = this.servico
    ) = copy(
        id = id,
        nome = nome,
        status = status,
        responsavel = responsavel,
        servico = servico
    )

    private fun copy(
        id: IdPrestadorClinicaHospital = this.id,
        nome: String = this.nome,
        status: Status = this.status,
        responsavel: PrestadorMedico = this.responsavel,
        servico: String = this.servico
    ) = PrestadorClinicaHospital(
        id = id,
        nome = nome,
        status = status,
        responsavel = responsavel,
        servico = servico
    )
}

 */