package br.unipar.plano.domain.procedimento.usecases.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.procedimento.model.IdProcedimento

class ProcedimentoNotFoundException(idProcedimento: IdProcedimento) : NotFoundException("Procedimento com id ${idProcedimento.id} não encontrado")
