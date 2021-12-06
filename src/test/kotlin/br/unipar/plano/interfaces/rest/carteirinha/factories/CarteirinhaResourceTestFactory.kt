package br.unipar.plano.interfaces.rest.carteirinha.factories

import br.unipar.plano.domain.carteirinha.model.factories.CARTEIRINHA_ID_USUARIO
import br.unipar.plano.domain.carteirinha.model.factories.CARTEIRINHA_NUMERO_CARTEIRINHA_VALIDO
import br.unipar.plano.interfaces.rest.carteirinha.CarteirinhaDTO

fun carteirinhaDTO(
        numeroCarteirinha: String = CARTEIRINHA_NUMERO_CARTEIRINHA_VALIDO,
        idUsuario: Int = CARTEIRINHA_ID_USUARIO
) = CarteirinhaDTO(
        numeroCarteirinha = numeroCarteirinha,
        idUsuario = idUsuario
)