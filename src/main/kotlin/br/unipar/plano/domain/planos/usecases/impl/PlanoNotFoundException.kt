package br.unipar.plano.domain.planos.usecases.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.planos.model.IdPlano

class PlanoNotFoundException(idPlano: IdPlano) : NotFoundException("Plano com id ${idPlano.id} não encontrado")
