package br.unipar.plano.domain.contratos.cobrancas.model

import br.unipar.plano.domain.contratos.cobrancas.valueobjects.StatusCobranca
import javax.persistence.*


@Entity
class Cobranca(
    @EmbeddedId
    val id: IdCobranca,

    @Column(nullable = false)
    var status: StatusCobranca = StatusCobranca.ABERTO,

) {

    fun cancelar(): Cobranca {
        if (status != StatusCobranca.ABERTO && status != StatusCobranca.PAGO) {
            throw IllegalStateException("Não é possível cancelar uma Cobranca com status $status")
        }
        return copy(status = StatusCobranca.CANCELADO)
    }

    private fun copy(
        id: IdCobranca = this.id,
        status: StatusCobranca = this.status,
    ) = Cobranca(
        id = id,
        status = status,
    )
}