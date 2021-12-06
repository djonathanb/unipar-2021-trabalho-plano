package br.unipar.plano.domain.procedimento.usecases

import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Procedimento

interface AtualizaProcedimentoUseCase {

    fun executa(idProcedimento: IdProcedimento, transformation: (Procedimento) -> Procedimento)

}
