package br.unipar.plano.domain.carteirinha.usecases

import br.unipar.plano.domain.carteirinha.model.Carteirinha

interface RegistraCarteirinhaUseCase {
    fun registra(numeroCarteirinha: String): Carteirinha
}