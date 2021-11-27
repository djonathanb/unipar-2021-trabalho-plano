package br.unipar.plano.domain.centrais.services

import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.centrais.model.Transporte

interface TransporteQueryService {
    fun lista(): List<Transporte>
    fun buscaPorId(idTransporte: IdTransporte): Transporte
}
