package br.unipar.plano.domain.procedimento.services

import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Procedimento
import org.springframework.stereotype.Service

interface ProcedimentoQueryService {
    fun lista(): List<Procedimento>
    fun buscaPorId(idProcedimento: IdProcedimento): Procedimento
}
