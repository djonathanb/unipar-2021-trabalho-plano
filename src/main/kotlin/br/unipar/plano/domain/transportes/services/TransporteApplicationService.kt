package br.unipar.plano.domain.centrais.services

import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.interfaces.rest.transportes.TransporteDTO
import br.unipar.plano.interfaces.rest.transportes.TransporteDetailsDTO
import br.unipar.plano.interfaces.rest.transportes.TransporteSummaryDTO

interface TransporteApplicationService {
    fun cria(transporteDTO: TransporteDTO): IdTransporte
    fun lista(): List<TransporteSummaryDTO>
    fun buscaPorId(idTransporte: IdTransporte): TransporteDetailsDTO
}