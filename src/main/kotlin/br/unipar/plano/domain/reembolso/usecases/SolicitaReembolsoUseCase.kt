package br.unipar.plano.domain.reembolso.usecases

import br.unipar.plano.domain.reembolso.model.Reembolso

interface SolicitaReembolsoUseCase {
    fun executa(reembolso: Reembolso): Reembolso
}