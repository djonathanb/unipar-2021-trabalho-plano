package br.unipar.plano.domain.cobrancas.service

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.service.impl.CobrancaNotFoundException
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import java.util.*

interface CobrancaQueryService {
    fun lista(): List<Cobranca>

    @Throws(CobrancaNotFoundException::class)
    fun buscaPorId(idCobranca: IdCobranca): Cobranca

    @Throws(CobrancaNotFoundException::class)
    fun buscarPorContratoAndStatus(contrato: Contrato, status: Optional<StatusCobranca>): List<Cobranca>
}
