package br.unipar.plano.domain.reembolso.services

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.model.Reembolso

interface ReembolsoQueryService {
    fun lista(): List<Reembolso>
    fun buscaPorId(idReembolso: IdReembolso): Reembolso
}
