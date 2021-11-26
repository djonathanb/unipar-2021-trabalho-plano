package br.unipar.plano.domain.carteirinha.usecases

import br.unipar.plano.domain.carteirinha.model.Carteirinha

interface VerificaCarteirinhaUseCase {
    fun executa(carteirinha: Carteirinha): Carteirinha
}