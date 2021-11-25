package br.unipar.plano.domain.contratos.services

import br.unipar.plano.domain.centrais.model.Contrato
import br.unipar.plano.domain.centrais.model.IdContrato

interface ContratoQueryService {

    fun lista(): List<Contrato>
    fun buscaPorId(idContrato: IdContrato): Contrato
}