package br.unipar.plano.domain.procedimento.usecases

import br.unipar.plano.domain.procedimento.model.IdProcedimento

interface DeletaProcedimentoUseCase {

    fun executa(idProcedimento: IdProcedimento)

}
