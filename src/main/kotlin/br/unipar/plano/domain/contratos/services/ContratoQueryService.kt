package br.unipar.plano.domain.contratos.services


import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.interfaces.rest.contratos.ContratoDTO


interface ContratoQueryService {

    fun lista(): List<Contrato>
    fun buscaPorId(idContrato: IdContrato): Contrato
}