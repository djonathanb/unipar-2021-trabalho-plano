package br.unipar.plano.domain.carteirinha.usecases.impl

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.CarteirinhaRepository
import br.unipar.plano.domain.carteirinha.usecases.CriaCarteirinhaUseCase
import org.springframework.stereotype.Service

@Service
class CriaCarteirinhaUseCaseImpl(private val carteirinhaRepository: CarteirinhaRepository): CriaCarteirinhaUseCase {
    override fun cria(carteirinha: Carteirinha): Carteirinha {
        return carteirinhaRepository.save(carteirinha)
    }
}