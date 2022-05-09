package br.unipar.plano.domain.cobrancas.gateway

import br.unipar.plano.domain.cobrancas.model.Cobranca

interface CobrancaGateway {

    fun salvar(cobranca: Cobranca): Cobranca
    fun remover(cobranca: Cobranca)

}