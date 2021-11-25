package br.unipar.plano.domain.contratos.usecases.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.centrais.model.IdContrato

class ContratoNotFoundException(idContrato: IdContrato) : NotFoundException("Contrato com id ${idContrato.id} não encontrado")