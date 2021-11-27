package br.unipar.plano.domain.carteirinha.services

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import org.springframework.stereotype.Service

@Service
interface CarteirinhaQueryService {
    fun buscaPorId(idCarteirinha: String): Carteirinha
}