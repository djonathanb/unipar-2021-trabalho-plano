package br.unipar.plano.domain.contratos.planos.services

import br.unipar.plano.domain.contratos.planos.model.IdPlano

import br.unipar.plano.interfaces.rest.contratos.planos.PlanoDTO
import br.unipar.plano.interfaces.rest.contratos.planos.PlanoDetailsDTO
import br.unipar.plano.interfaces.rest.contratos.planos.PlanoSummaryDTO


interface PlanoApplicationService {

    fun cria(planoDTO: PlanoDTO): IdPlano
    fun deleta(idPlano: IdPlano)
    fun lista() : List<PlanoSummaryDTO>
    fun buscaPorId(idPlano: IdPlano): PlanoDetailsDTO

}