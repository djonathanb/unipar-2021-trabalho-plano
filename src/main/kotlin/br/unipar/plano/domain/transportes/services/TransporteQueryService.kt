package br.unipar.plano.domain.centrais.services

import br.unipar.plano.domain.centrais.model.Transporte
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.model.IdTransporte

interface TransporteQueryService {
    fun lista(): List<Transporte>
    fun buscaPorId(idTransporte: IdTransporte): Transporte
}
