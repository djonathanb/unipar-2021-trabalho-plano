package br.unipar.plano.domain.cobrancas.service.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.cobrancas.model.IdCobranca

class CobrancaNotFoundException(idCobranca: IdCobranca) : NotFoundException("Central com id: ${idCobranca.id} não encontrada.")
