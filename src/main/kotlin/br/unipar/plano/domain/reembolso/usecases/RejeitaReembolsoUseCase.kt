package br.unipar.plano.domain.reembolso.usecases

import br.unipar.plano.domain.reembolso.model.IdReembolso

interface RejeitaReembolsoUseCase {
    fun executa(idReembolso: IdReembolso)
}