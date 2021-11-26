package br.unipar.plano.domain.contratos.model


import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.StatusCentral
import java.time.LocalDate
import java.util.*
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
    val idPlano: UUID,

    @Column(nullable = false)
    val idTitular: UUID,

    @Enumerated(EnumType.STRING)
    val status: StatusContrato = StatusContrato.ATIVO
) {
    fun with(
        idContrato: IdContrato = this.idContrato,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        idPlano: UUID = this.idPlano
    ) = copy(
        idContrato = idContrato,
        dataContratoFinal = dataContratoFinal,
        idPlano = idPlano
    )

    private fun copy(
        idContrato: IdContrato = this.idContrato,
        dataContratacao: LocalDate = this.dataContratacao,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        idPlano: UUID = this.idPlano,
        idTitular: UUID = this.idTitular,
        status: StatusContrato = this.status
    ) = Contrato(
        idContrato = idContrato,
        dataContratacao = dataContratacao,
        dataContratoFinal = dataContratoFinal,
        idPlano = idPlano,
        idTitular = idTitular,
        status = status
    )

    fun cancela(): Contrato {
        if (status != StatusContrato.ATIVO) {
            throw IllegalStateException("Não é possível cancelar um Contrato com status $status")
        }
        return copy(status = StatusContrato.CANCELADO)
    }
}