package br.unipar.plano.domain.contratos.model

import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.planos.model.Plano
import java.time.LocalDate
import javax.persistence.*

enum class StatusContrato {
    ATIVO, CANCELADO
}

@Entity
class Contrato(

    @field:EmbeddedId
    val id: IdContrato,

    @Column(nullable = false)
    val dataContratacao: LocalDate,

    @Column(nullable = false)
    val dataContratoFinal: LocalDate,

    @ManyToOne
    val plano: Plano,

    @OneToOne
    val titular: Pessoa,

    @Enumerated(EnumType.STRING)
    val status: StatusContrato = StatusContrato.ATIVO
) {
    fun with(
        idContrato: IdContrato = this.id,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        plano: Plano = this.plano
    ) = copy(
        idContrato = idContrato,
        dataContratoFinal = dataContratoFinal,
        plano = plano
    )

    private fun copy(
        idContrato: IdContrato = this.id,
        dataContratacao: LocalDate = this.dataContratacao,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        plano: Plano = this.plano,
        Titular: Pessoa = this.titular,
        status: StatusContrato = this.status
    ) = Contrato(
        id = idContrato,
        dataContratacao = dataContratacao,
        dataContratoFinal = dataContratoFinal,
        plano = plano,
        titular = Titular,
        status = status
    )

    fun cancela(): Contrato {
        if (status != StatusContrato.ATIVO) {
            throw IllegalStateException("Não é possível cancelar um Contrato com status $status")
        }
        return copy(status = StatusContrato.CANCELADO)
    }
}