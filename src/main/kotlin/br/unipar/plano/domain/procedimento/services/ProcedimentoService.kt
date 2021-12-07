package br.unipar.plano.domain.procedimento.services

import br.unipar.plano.domain.prestador.model.IdPrestador
import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Procedimento

interface ProcedimentoService {
    fun lista(idPrestador: IdPrestador, year: Int, month: Int): List<Procedimento>
    fun buscaPorId(idProcedimento: IdProcedimento): Procedimento
}
