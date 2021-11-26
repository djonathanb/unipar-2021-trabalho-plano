package br.unipar.plano.domain.carteirinha.services

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.IdCarteirinha
import br.unipar.plano.domain.usuario.IdUsuario

interface CarteirinhaApplicationService {
    fun criar(idUsuario: IdUsuario): IdCarteirinha;
    fun validaCarteirinha(idCarteirinha: IdCarteirinha): Carteirinha
    fun registraExtravio(idUsuario: IdUsuario, motivo: String): Carteirinha
    fun registraEntrega(idCarteirinha: IdCarteirinha): Carteirinha

}