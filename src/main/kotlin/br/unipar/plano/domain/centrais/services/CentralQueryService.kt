package br.unipar.plano.domain.centrais.services

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.IdCentral

interface CentralQueryService {
    fun lista(): List<Central>
    fun buscaPorId(idCentral: IdCentral): Central
}
