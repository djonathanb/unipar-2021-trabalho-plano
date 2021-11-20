package br.unipar.plano.domain.credenciamentos.model

import org.hibernate.validator.constraints.br.CNPJ
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

    @Column(nullable = false)
    val servico: String

) {

    fun with(
        id: IdPrestadorClinicaHospital = this.id,
        nome: String = this.nome,
        cnpj: String = this.cnpj,
        status: Status = this.status,
        responsavel: PrestadorMedico = this.responsavel,
        servico: String = this.servico
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
        servico: String = this.servico
    ) = PrestadorClinicaHospital(
        id = id,
        nome = nome,
        cnpj = cnpj,
        status = status,
        responsavel = responsavel,
        servico = servico
    )
}