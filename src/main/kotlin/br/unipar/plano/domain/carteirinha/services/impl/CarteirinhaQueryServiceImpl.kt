package br.unipar.plano.domain.carteirinha.services.impl

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.CarteirinhaRepository
import br.unipar.plano.domain.carteirinha.services.CarteirinhaQueryService
import org.springframework.stereotype.Service

@Service
class CarteirinhaQueryServiceImpl(private val carteirinhaRepository: CarteirinhaRepository): CarteirinhaQueryService {
    override fun buscaPorId(idCarteirinha: String): Carteirinha = carteirinhaRepository.findById(idCarteirinha).orElseThrow {
        Exception("Carteirinha com o id ${idCarteirinha} não encontrada!")
    }

    override fun save(carteirinha: Carteirinha): Carteirinha {
        return carteirinhaRepository.save(carteirinha);
    }

    override fun findByIdUsuario(idUsuario: Int): Carteirinha {
        return carteirinhaRepository.findByIdUsuario(idUsuario).orElseThrow{
            Exception("Nenhuma carteirinha Válida encontrada para o Usuário $idUsuario")
        };
    }

}