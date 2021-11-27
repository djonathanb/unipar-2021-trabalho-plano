package br.unipar.plano.domain.carteirinha.usecases.impl

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.CarteirinhaRepository
import br.unipar.plano.domain.carteirinha.usecases.CriaCarteirinhaUseCase
import org.springframework.stereotype.Service

@Service
class CriaCarteirinhaUseCaseImpl(private val carteirinhaRepository: CarteirinhaRepository): CriaCarteirinhaUseCase {
    override fun cria(carteirinha: Carteirinha): Carteirinha {

        var carteirinhaResult = carteirinhaRepository.findByIdUsuario(carteirinha.idUsuario)

        if (carteirinhaResult.isPresent)
            throw Exception("Usuário ${carteirinha.idUsuario} já possui uma carteirinha válida: ${carteirinhaResult.get().numeroCarteirinha}")

        carteirinhaResult = carteirinhaRepository.findById(carteirinha.numeroCarteirinha)

        if (carteirinhaResult.isPresent)
            throw Exception("A carteirinha ${carteirinha.numeroCarteirinha} já está cadastrada")

        return carteirinhaRepository.save(carteirinha)
    }
}