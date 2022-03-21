package br.unipar.plano.domain.procedimento.usecases

import br.unipar.plano.domain.procedimento.model.Procedimento

interface CriaProcedimentoUseCase {
    fun executa(procedimento: Procedimento): Procedimento
}