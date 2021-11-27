package br.unipar.plano.domain.carteirinha.services

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.MotivoExtravio
import br.unipar.plano.interfaces.rest.carteirinha.CarteirinhaDTO
import br.unipar.plano.interfaces.rest.carteirinha.MotivoDTO

interface CarteirinhaApplicationService {
    fun criar(dto: CarteirinhaDTO): String;
    fun validaCarteirinha(dto: CarteirinhaDTO): Carteirinha
    fun registraExtravio(dto: MotivoDTO): MotivoExtravio
    fun registraEntrega(dto: CarteirinhaDTO): Carteirinha

}