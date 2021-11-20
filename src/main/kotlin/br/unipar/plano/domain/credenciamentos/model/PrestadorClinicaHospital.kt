package br.unipar.plano.domain.credenciamentos.model

import javax.persistence.*


@Entity
class PrestadorClinicaHospital(

    @field:EmbeddedId
    val id: IdPrestadorClinicaHospital,

    @Column(nullable = false)
    val nome: String,

    @Column
    val cnpj: String,

    @Enumerated(EnumType.STRING)
    val status: Status,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    val responsavel: PrestadorMedico,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    val servico: Servico

) {

    fun with(
        id: IdPrestadorClinicaHospital = this.id,
        nome: String = this.nome,
        cnpj: String = this.cnpj,
        status: Status = this.status,
        responsavel: PrestadorMedico = this.responsavel,
        servico: Servico = this.servico
    ) = copy(
        id = id,
        nome = nome,
        cnpj = cnpj,
        status = status,
        responsavel = responsavel,
        servico = servico
    )

    private fun copy(
        id: IdPrestadorClinicaHospital = this.id,
        nome: String = this.nome,
        cnpj: String = this.cnpj,
        status: Status = this.status,
        responsavel: PrestadorMedico = this.responsavel,
        servico: Servico = this.servico
    ) = PrestadorClinicaHospital(
        id = id,
        nome = nome,
        cnpj = cnpj,
        status = status,
        responsavel = responsavel,
        servico = servico
    )
}