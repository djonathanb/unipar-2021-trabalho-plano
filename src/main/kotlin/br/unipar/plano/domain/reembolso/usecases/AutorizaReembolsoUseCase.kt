package br.unipar.plano.domain.reembolso.usecases

import br.unipar.plano.domain.reembolso.model.IdReembolso

interface AutorizaReembolsoUseCase {
    fun executa(idReembolso: IdReembolso)
}