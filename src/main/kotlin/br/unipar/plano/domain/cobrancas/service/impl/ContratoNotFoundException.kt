package br.unipar.plano.domain.cobrancas.service.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.cobrancas.model.IdContrato

class ContratoNotFoundException(idContrato: IdContrato) :
    NotFoundException("Contrato com id: ${idContrato.id} não encotrado.")
