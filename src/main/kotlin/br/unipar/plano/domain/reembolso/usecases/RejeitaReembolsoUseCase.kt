package br.unipar.plano.domain.reembolso.usecases

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.model.RejeicaoReembolso

interface RejeitaReembolsoUseCase {
    fun executa(idReembolso: IdReembolso, rejeicaoReembolso: RejeicaoReembolso)
}