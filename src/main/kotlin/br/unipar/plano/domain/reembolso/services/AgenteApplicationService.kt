package br.unipar.plano.domain.reembolso.services

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.interfaces.dto.ReembolsoDetailsDTO
import br.unipar.plano.interfaces.dto.ReembolsoSummaryDTO

interface AgenteApplicationService {

    fun autorizarReembolso(idReembolso: IdReembolso)
    fun rejeitarReembolso(idReembolso: IdReembolso)
    fun lista(): List<ReembolsoSummaryDTO>
    fun buscaPorId(idReembolso: IdReembolso): ReembolsoDetailsDTO
}
