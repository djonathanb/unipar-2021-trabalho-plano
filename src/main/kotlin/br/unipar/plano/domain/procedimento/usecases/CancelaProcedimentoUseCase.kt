package br.unipar.plano.domain.procedimento.usecases

import br.unipar.plano.domain.procedimento.model.IdProcedimento

interface CancelaProcedimentoUseCase {
    fun executa(idProcedimento: IdProcedimento)
}
