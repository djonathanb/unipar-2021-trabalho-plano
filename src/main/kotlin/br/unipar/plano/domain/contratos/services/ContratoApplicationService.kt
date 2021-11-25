package br.unipar.plano.domain.contratos.services

import br.unipar.plano.domain.centrais.model.IdContrato

interface ContratoApplicationService {

    fun cria(contratoDTO: ContratoDTO): IdContrato
    fun lista(): List<ContratoSummaryDTO>
    fun buscaPorId(idContrato: IdContrato): ContratoDetailsDTO
    fun deleta(idContrato: IdContrato)
}