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

    @Column(nullable = false)
    val dataCancelamento: LocalDate?,

    @ManyToOne
    val plano: Plano,

    @OneToOne
    val titular: Pessoa,

    @Enumerated(EnumType.STRING)
    val status: StatusContrato = StatusContrato.ATIVO
) {
    fun with(
        id: IdContrato = this.id,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        plano: Plano = this.plano
    ) = copy(
        id = id,
        dataContratoFinal = dataContratoFinal,
        plano = plano
    )

    private fun copy(
        id : IdContrato = this.id,
        dataContratacao: LocalDate = this.dataContratacao,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        plano: Plano = this.plano,
        Titular: Pessoa = this.titular,
        status: StatusContrato = this.status,
        dataCancelamento: LocalDate? = this.dataCancelamento
    ) = Contrato(
        id = id,
        dataContratacao = dataContratacao,
        dataContratoFinal = dataContratoFinal,
        plano = plano,
        titular = Titular,
        status = status,
        dataCancelamento = dataCancelamento
    )

    fun cancela(): Contrato {
        if (status != StatusContrato.ATIVO) {
            throw IllegalStateException("Não é possível cancelar um Contrato com status $status")
        }
        return copy(status = StatusContrato.CANCELADO, dataCancelamento = LocalDate.now())
    }
}