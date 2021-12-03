package br.unipar.plano.domain.centrais.services

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.interfaces.rest.centrais.dto.CentralDTO
import br.unipar.plano.interfaces.rest.centrais.dto.CentralDetailsDTO
import br.unipar.plano.interfaces.rest.centrais.dto.CentralSummaryDTO

interface CentralApplicationService {

    fun cria(centralDTO: CentralDTO): IdCentral
    fun atualiza(idCentral: IdCentral, centralDTO: CentralDTO)
    fun lista(): List<CentralSummaryDTO>
    fun buscaPorId(idCentral: IdCentral): CentralDetailsDTO
    fun credenciar(idCentral: IdCentral)
    fun descredenciar(idCentral: IdCentral)
    fun deleta(idCentral: IdCentral)

}