package br.unipar.plano.domain.reembolso.usecases.exceptions

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.reembolso.model.IdReembolso

class ReembolsoNotFoundException(idReembolso: IdReembolso) :
        NotFoundException("Reembolso com id ${idReembolso.id} não encontrado")
