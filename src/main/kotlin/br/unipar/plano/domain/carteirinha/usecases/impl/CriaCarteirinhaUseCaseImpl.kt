package br.unipar.plano.domain.carteirinha.usecases.impl

import br.unipar.plano.application.exceptions.CarteirinhaCadastradaException
import br.unipar.plano.application.exceptions.CarteirinhaUsuarioCadastradaException
import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.CarteirinhaRepository
import br.unipar.plano.domain.carteirinha.usecases.CriaCarteirinhaUseCase
import org.springframework.stereotype.Service
import java.util.*

@Service
class CriaCarteirinhaUseCaseImpl(private val carteirinhaRepository: CarteirinhaRepository): CriaCarteirinhaUseCase {
    override fun cria(carteirinha: Carteirinha): Carteirinha {

        var carteirinhaResult: Optional<Carteirinha> = carteirinhaRepository.findCarteirinhaValidaByIdUSuario(carteirinha.idUsuario)

        if (carteirinhaResult.isPresent)
            throw CarteirinhaUsuarioCadastradaException(carteirinhaResult.get().numeroCarteirinha, carteirinha.idUsuario)

        carteirinhaResult = carteirinhaRepository.findById(carteirinha.numeroCarteirinha)

        if (carteirinhaResult.isPresent)
            throw CarteirinhaCadastradaException(carteirinha.numeroCarteirinha)

        return carteirinhaRepository.save(carteirinha)
    }
}