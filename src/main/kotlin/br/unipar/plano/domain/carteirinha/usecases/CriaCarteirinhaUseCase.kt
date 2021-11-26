package br.unipar.plano.domain.carteirinha.usecases

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.IdCarteirinha
import org.springframework.stereotype.Service

@Service
interface CriaCarteirinhaUseCase {
    fun cria(carteirinha: Carteirinha): Carteirinha
}