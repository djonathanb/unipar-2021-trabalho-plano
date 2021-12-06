package br.unipar.plano.domain.procedimento.services

import br.unipar.plano.domain.prestador.model.IdPrestador
import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoDTO
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoDetailsDTO
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoSummaryDTO

interface ProcedimentoQueryService {
    fun cria(procedimentoDTO: ProcedimentoDTO): IdProcedimento
    fun lista(idPrestador: IdPrestador, year: Int, month: Int): List<ProcedimentoSummaryDTO>
    fun buscaPorId(idProcedimento: IdProcedimento): ProcedimentoDetailsDTO
    fun cancela(idProcedimento: IdProcedimento)
}