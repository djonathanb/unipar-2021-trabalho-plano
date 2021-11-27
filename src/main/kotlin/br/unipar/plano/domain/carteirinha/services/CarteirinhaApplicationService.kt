package br.unipar.plano.domain.carteirinha.services

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.interfaces.rest.carteirinha.CarteirinhaDTO

interface CarteirinhaApplicationService {
    fun criar(dto: CarteirinhaDTO): String;
    fun validaCarteirinha(dto: CarteirinhaDTO): Carteirinha
    fun registraExtravio(idUsuario: Int, motivo: String): Carteirinha
    fun registraEntrega(dto: CarteirinhaDTO): Carteirinha

}