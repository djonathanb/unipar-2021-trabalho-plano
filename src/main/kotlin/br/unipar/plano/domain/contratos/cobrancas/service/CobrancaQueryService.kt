package br.unipar.plano.domain.contratos.cobrancas.service

import br.unipar.plano.domain.contratos.cobrancas.model.Cobranca
import br.unipar.plano.domain.contratos.cobrancas.service.impl.CobrancaNotFoundException
import br.unipar.plano.domain.contratos.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.domain.contratos.model.Contrato
import java.util.*

interface CobrancaQueryService {

    @Throws(CobrancaNotFoundException::class)
    fun buscarPorContratoAndStatus(contrato: Contrato, status: Optional<List<StatusCobranca>>): List<Cobranca>

}
