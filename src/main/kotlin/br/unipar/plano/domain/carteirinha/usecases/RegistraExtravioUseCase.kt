package br.unipar.plano.domain.carteirinha.usecases

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import org.springframework.stereotype.Service

@Service
interface RegistraExtravioUseCase {
    fun RegistraExtravio(carteirinha: Carteirinha): Carteirinha
}