package br.unipar.plano.domain.carteirinhas.model

import javax.persistence.*

@Entity
class Carteirinha(
    @field:EmbeddedId
    val id: IdCarteirinha,
) {
      fun with(
        id: IdCarteirinha = this.id,
    ) = copy(
        id = id,
    )

    private fun copy(
        id: IdCarteirinha = this.id,
    ) = Carteirinha(
        id = id,
    )

}
