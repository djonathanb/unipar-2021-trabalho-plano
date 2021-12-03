package br.unipar.plano.domain.carteirinha.usecases.impl

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.services.CarteirinhaQueryService
import br.unipar.plano.domain.carteirinha.usecases.RegistraExtravioUseCase
import org.springframework.stereotype.Service

@Service
class RegistraExtravioUseCaseImpl(private val carteirinhaQueryService: CarteirinhaQueryService) : RegistraExtravioUseCase {

    override fun registraExtravio(carteirinha: Carteirinha): Carteirinha {

        var carteirinhaResult: Carteirinha = carteirinha.registrarExtravio()

        return carteirinhaQueryService.save(carteirinhaResult)
    }
}