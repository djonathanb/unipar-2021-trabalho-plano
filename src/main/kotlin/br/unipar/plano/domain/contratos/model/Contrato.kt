package br.unipar.plano.domain.contratos.model

import br.unipar.plano.domain.pessoas.model.Pessoa
import org.hibernate.annotations.CascadeType
import java.time.LocalDate
import java.util.*
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

    @Column(nullable = false, name = "id_plano")
    @ManyToOne
    val plano: Plano,

    @Column(nullable = false, name = "id_titular")
    @OneToOne
    val titular: Pessoa,

    @Enumerated(EnumType.STRING)
    val status: StatusContrato = StatusContrato.ATIVO
) {
    fun with(
        idContrato: IdContrato = this.id,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        idPlano: Plano = this.plano
    ) = copy(
        idContrato = idContrato,
        dataContratoFinal = dataContratoFinal,
        idPlano = idPlano
    )

    private fun copy(
        idContrato: IdContrato = this.id,
        dataContratacao: LocalDate = this.dataContratacao,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        idPlano: Plano = this.plano,
        idTitular: Pessoa = this.titular,
        status: StatusContrato = this.status
    ) = Contrato(
        id = idContrato,
        dataContratacao = dataContratacao,
        dataContratoFinal = dataContratoFinal,
        plano = idPlano,
        titular = idTitular,
        status = status
    )

    fun cancela(): Contrato {
        if (status != StatusContrato.ATIVO) {
            throw IllegalStateException("Não é possível cancelar um Contrato com status $status")
        }
        return copy(status = StatusContrato.CANCELADO)
    }
}