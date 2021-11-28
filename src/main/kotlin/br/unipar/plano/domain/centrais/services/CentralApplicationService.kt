package br.unipar.plano.domain.centrais.services

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.interfaces.rest.centrais.CentralDTO
import br.unipar.plano.interfaces.rest.centrais.CentralDetailsDTO
import br.unipar.plano.interfaces.rest.centrais.CentralSummaryDTO

interface CentralApplicationService {

    fun cria(centralDTO: CentralDTO): IdCentral
    fun atualiza(idCentral: IdCentral, centralDTO: CentralDTO)
    fun lista(): List<CentralSummaryDTO>
    fun buscaPorId(idCentral: IdCentral): CentralDetailsDTO
    fun credenciar(idCentral: IdCentral)
    fun descredenciar(idCentral: IdCentral)
    fun deleta(idCentral: IdCentral)

}