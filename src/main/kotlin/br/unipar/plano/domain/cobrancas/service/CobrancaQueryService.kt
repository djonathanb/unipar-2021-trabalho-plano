package br.unipar.plano.domain.cobrancas.service

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.service.impl.CobrancaNotFoundException

interface CobrancaQueryService {
    fun lista(): List<Cobranca>
    @Throws(CobrancaNotFoundException::class)
    fun buscaPorId(idCobranca: IdCobranca): Cobranca
}
