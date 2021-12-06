package br.unipar.plano.domain.procedimento.services

import br.unipar.plano.domain.procedimento.model.IdProcedimento

interface CancelaProcedimentoUseCase {

    fun executa(idProcedimento: IdProcedimento)

}
