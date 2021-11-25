package br.unipar.plano.domain.contratos.model


import java.time.LocalDate
import javax.persistence.*

enum class StatusContrato {
    ATIVO, CANCELADO
}

@Entity
class Contrato(

    @field:EmbeddedId
    val idContrato: IdContrato,

    @Column(nullable = false)
    val dataContratacao: LocalDate,

    @Column(nullable = false)
    val dataContratoFinal: LocalDate,

    @Column(nullable = false)
    val idPlano: Int,

    @Column(nullable = false)
    val idTitular: Int,

    @Enumerated(EnumType.STRING)
    val status: StatusContrato = StatusContrato.ATIVO
) {
    fun with(
        idContrato: IdContrato = this.idContrato,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        idPlano: Int = this.idPlano
    ) = copy(
        idContrato = idContrato,
        dataContratoFinal = dataContratoFinal,
        idPlano = idPlano
    )

    private fun copy(
        idContrato: IdContrato = this.idContrato,
        dataContratacao: LocalDate = this.dataContratacao,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        idPlano: Int = this.idPlano,
        idTitular: Int = this.idTitular,
        status: StatusContrato = this.status
    ) = Contrato(
        idContrato = idContrato,
        dataContratacao = dataContratacao,
        dataContratoFinal = dataContratoFinal,
        idPlano = idPlano,
        idTitular = idTitular,
        status = status
    )
}