package br.unipar.plano.domain.contratos.planos.usecases.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.contratos.planos.model.IdPlano


class PlanoNotFoundException(idPlano: IdPlano) : NotFoundException("Plano com id ${idPlano.id} não encontrado")
