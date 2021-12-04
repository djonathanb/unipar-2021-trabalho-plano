package br.unipar.plano.domain.contratos.cobrancas.service


import br.unipar.plano.domain.contratos.cobrancas.service.impl.CobrancaNotFoundException
import br.unipar.plano.domain.contratos.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.interfaces.rest.contratos.cobrancas.CobrancaDetailsDTO

import java.util.*

interface CobrancaService {

    @Throws(CobrancaNotFoundException::class)
    fun buscarPorContratoAndStatus(contrato: Contrato, status: Optional<List<StatusCobranca>>): List<CobrancaDetailsDTO>
}