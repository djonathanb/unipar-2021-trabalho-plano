package br.unipar.plano.domain.contratos.services

import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.contratos.planos.model.Plano
import br.unipar.plano.interfaces.rest.contratos.ContratoDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoDetailsDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoSummaryDTO

interface ContratoApplicationService {

    fun cria(contratoDTO: ContratoDTO): IdContrato
    fun lista(): List<ContratoSummaryDTO>
    fun buscaPorId(idContrato: IdContrato): ContratoDetailsDTO
    fun atualiza(idContrato: IdContrato, contratoDTO: ContratoDTO)
    fun buscaPorPlano(idPlano: Plano): List<ContratoDetailsDTO>
    fun buscaPorPlanoeStatus(plano: Plano, status: Optional<List<StatusContrato>>): List<ContratoSummaryDTO>
    fun cancelaContrato(idContrato: IdContrato)

}