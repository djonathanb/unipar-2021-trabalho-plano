package br.unipar.plano.interfaces.rest.carteirinha

import javax.validation.constraints.NotNull

class CarteirinhaDTO (
    val numeroCarteirinha: String,
    val idUsuario: Int
) {

}

data class MotivoDTO(
        @field:NotNull
        val idUsuario: Int,
        @field:NotNull
        val motivo: String
)