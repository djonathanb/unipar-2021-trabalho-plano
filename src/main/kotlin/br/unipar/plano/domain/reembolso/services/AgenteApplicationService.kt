package br.unipar.plano.domain.reembolso.services

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoDetailsDTO
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoRejeitadoDTO
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoSummaryDTO

interface AgenteApplicationService {

    fun autorizarReembolso(idReembolso: IdReembolso)
    fun rejeitarReembolso(idReembolso: IdReembolso, reembolsoRejeitadoDTO: ReembolsoRejeitadoDTO)
    fun lista(): List<ReembolsoSummaryDTO>
    fun buscaPorId(idReembolso: IdReembolso): ReembolsoDetailsDTO
}
