package br.unipar.plano.domain.planos.services

import br.unipar.plano.domain.planos.model.IdPlano
import br.unipar.plano.interfaces.rest.planos.PlanoDetailsDTO
import br.unipar.plano.interfaces.rest.planos.PlanoDTO
import br.unipar.plano.interfaces.rest.planos.PlanoSummaryDTO

interface PlanoApplicationService {

    fun cria(planoDTO: PlanoDTO): IdPlano
    fun deleta(idPlano: IdPlano)
    fun lista() : List<PlanoSummaryDTO>
    fun buscaPorId(idPlano: IdPlano): PlanoDetailsDTO

}