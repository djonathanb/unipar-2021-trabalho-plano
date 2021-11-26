package br.unipar.plano.domain.carteirinha.usecases.impl

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.CarteirinhaRepository
import br.unipar.plano.domain.carteirinha.usecases.VerificaCarteirinhaUseCase
import org.springframework.stereotype.Service

@Service
class VerificaCarteirinhaUseCaseImpl(private val carteirinhaRepository: CarteirinhaRepository): VerificaCarteirinhaUseCase {

    override fun executa(carteirinha: Carteirinha): Carteirinha {
        throw Exception()
    }

}