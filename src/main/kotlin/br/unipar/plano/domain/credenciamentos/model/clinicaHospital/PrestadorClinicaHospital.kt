package br.unipar.plano.domain.credenciamentos.model.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import javax.persistence.*

enum class StatusClinicaHospital{
    CRIADA, CREDENCIADA, DESCREDENCIADA
}

@Entity
class PrestadorClinicaHospital(

    @field:EmbeddedId
    val id: IdPrestadorClinicaHospital,

    @Column(nullable = false)
    val nome: String,

    @Column
    val cnpj: String,

    @Enumerated(EnumType.STRING)
    val status: StatusClinicaHospital = StatusClinicaHospital.CRIADA,

    @JoinColumn(nullable = false, name = "id")
    @OneToOne
    val responsavel: PrestadorMedico,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val servico: List<Servico>

) {

    fun credencia(): PrestadorClinicaHospital {
        if (status == StatusClinicaHospital.CREDENCIADA) {
            throw IllegalStateException("Não é possível credenciar uma Clinica ou Hospital com status $status")
        }
        return copy(status = StatusClinicaHospital.CREDENCIADA)
    }

    fun descredencia(): PrestadorClinicaHospital {
        if (status != StatusClinicaHospital.CREDENCIADA) {
            throw IllegalStateException("Não é possível descredenciar um médico com status $status")
        }
        return copy(status = StatusClinicaHospital.DESCREDENCIADA)
    }


    fun with(
        id: IdPrestadorClinicaHospital = this.id,
        nome: String = this.nome,
        cnpj: String = this.cnpj,
        status: StatusClinicaHospital = this.status,
        responsavel: PrestadorMedico = this.responsavel,
        servico: List<Servico> = this.servico
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
        status: StatusClinicaHospital = this.status,
        responsavel: PrestadorMedico = this.responsavel,
        servico: List<Servico> = this.servico
    ) = PrestadorClinicaHospital(
        id = id,
        nome = nome,
        cnpj = cnpj,
        status = status,
        responsavel = responsavel,
        servico = servico
    )
}