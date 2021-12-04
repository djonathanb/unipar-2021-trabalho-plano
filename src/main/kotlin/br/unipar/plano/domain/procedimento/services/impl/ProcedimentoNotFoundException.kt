package br.unipar.plano.domain.procedimento.services.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.procedimento.model.IdProcedimento

class ProcedimentoNotFoundException(idProcedimento: IdProcedimento) : NotFoundException("Procedimento com id ${idProcedimento} não encontrado")
