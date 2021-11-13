package br.unipar.plano.domain.centrais.services

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.interfaces.rest.centrais.CentralDTO

interface CentralApplicationService {

    fun criar(centralDTO: CentralDTO): IdCentral

}