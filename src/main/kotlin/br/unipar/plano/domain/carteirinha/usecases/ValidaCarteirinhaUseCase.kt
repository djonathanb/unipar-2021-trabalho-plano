package br.unipar.plano.domain.carteirinha.usecases

import br.unipar.plano.domain.carteirinha.model.Carteirinha

interface ValidaCarteirinhaUseCase {
    fun validar(carteirinha: Carteirinha): Carteirinha
}