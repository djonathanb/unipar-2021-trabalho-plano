package br.unipar.plano.domain.contratos.cobrancas.service.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.contratos.cobrancas.model.IdCobranca

class CobrancaNotFoundException(idCobranca: IdCobranca) : NotFoundException("Central com id: ${idCobranca.id} não encontrada.")
