package br.unipar.plano.domain.carteirinha.services

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.IdCarteirinha
import br.unipar.plano.domain.usuario.IdUsuario
import org.springframework.stereotype.Service

@Service
interface CarteirinhaQueryService {
    //fun criar(idUsuario: IdUsuario): IdCarteirinha
    fun buscaPorId(idCarteirinha: String): Carteirinha
}