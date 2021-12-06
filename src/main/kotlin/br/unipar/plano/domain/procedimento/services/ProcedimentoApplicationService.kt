package br.unipar.plano.domain.procedimento.services

import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoDTO
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoDetailsDTO
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoSummaryDTO

interface ProcedimentoApplicationService {
    fun cria(procedimentoDTO: ProcedimentoDTO): IdProcedimento
    fun atualiza(idProcedimento: IdProcedimento, procedimentoDTO: ProcedimentoDTO)
    fun lista(): List<ProcedimentoSummaryDTO>
    fun buscaPorId(idProcedimento: IdProcedimento): ProcedimentoDetailsDTO
    fun cancela(idProcedimento: IdProcedimento)
}