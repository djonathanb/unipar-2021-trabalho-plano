package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.centrais.model.IdCentral

class CentralNotFoundException(idCentral: IdCentral) : NotFoundException("Central com id ${idCentral.id} não encontrada")
