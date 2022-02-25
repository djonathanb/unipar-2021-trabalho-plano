package br.unipar.plano.domain.cobrancas.service

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import java.util.*

interface CobrancaQueryService {
    fun lista(idContrato: IdContrato): List<Cobranca>
    fun buscaPorId(idContrato: IdContrato, idCobranca: IdCobranca): Cobranca

    fun buscarPorContratoAndStatus(idContrato: IdContrato, status: Optional<List<StatusCobranca>>): List<Cobranca>
}
