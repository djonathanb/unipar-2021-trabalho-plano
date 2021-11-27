package br.unipar.plano.domain.carteirinha.usecases.impl

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.services.CarteirinhaQueryService
import br.unipar.plano.domain.carteirinha.usecases.ValidaCarteirinhaUseCase
import org.springframework.stereotype.Service

@Service
class ValidaCarteirinhaUseCaseImpl(private val carteirinhaQueryService: CarteirinhaQueryService) : ValidaCarteirinhaUseCase {

    override fun validar(carteirinha: Carteirinha): Carteirinha {
        val carteirinhaResult: Carteirinha
        try {
            carteirinhaResult = carteirinhaQueryService.buscaPorId(carteirinha.numeroCarteirinha)
        } catch (ex: Exception) {
            throw ex;
        }

        if (!carteirinhaResult.validate())
            throw Exception("Carteirinha Inválida:\n Status: ${carteirinhaResult.status}")

        return carteirinhaResult

    }

}