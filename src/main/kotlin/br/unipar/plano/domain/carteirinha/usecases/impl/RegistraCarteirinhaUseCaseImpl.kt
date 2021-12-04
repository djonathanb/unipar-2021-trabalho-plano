package br.unipar.plano.domain.carteirinha.usecases.impl

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.CarteirinhaRepository
import br.unipar.plano.domain.carteirinha.services.CarteirinhaQueryService
import br.unipar.plano.domain.carteirinha.usecases.RegistraCarteirinhaUseCase
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegistraCarteirinhaUseCaseImpl(private val carteirinhaQueryService: CarteirinhaQueryService) : RegistraCarteirinhaUseCase {

    override fun registra(numeroCarteirinha: String): Carteirinha {
        var carteirinhaResult: Carteirinha
        try {
            carteirinhaResult = carteirinhaQueryService.buscaPorId(numeroCarteirinha)
        } catch (ex: Exception) {
            throw ex;
        }

        carteirinhaResult = carteirinhaResult.registrarEntrega()

        return carteirinhaQueryService.save(carteirinhaResult)
    }
}