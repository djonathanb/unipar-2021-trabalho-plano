package br.unipar.plano.domain.carteirinha.usecases

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import org.springframework.stereotype.Service

@Service
interface CriaCarteirinhaUseCase {
    fun cria(carteirinha: Carteirinha): Carteirinha
}