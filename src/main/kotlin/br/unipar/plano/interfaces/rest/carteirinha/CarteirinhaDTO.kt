package br.unipar.plano.interfaces.rest.carteirinha

import br.unipar.plano.domain.usuario.IdUsuario
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