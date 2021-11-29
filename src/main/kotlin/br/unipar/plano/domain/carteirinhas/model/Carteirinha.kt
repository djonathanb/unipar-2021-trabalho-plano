package br.unipar.plano.domain.carteirinhas.model

import org.springframework.beans.factory.annotation.Value
import javax.persistence.*

@Entity
class Carteirinha(
    @field:EmbeddedId
    val id: IdCarteirinha,

    @Column
    val valida: Boolean,

) {
    fun with(
        id: IdCarteirinha = this.id,
        valida: Boolean = this.valida,
    ) = copy(
        id = id,
        valida = valida,
    )

    private fun copy(
        id: IdCarteirinha = this.id,
        valida: Boolean = this.valida,
    ) = Carteirinha(
        id = id,
        valida = valida,
    )

}