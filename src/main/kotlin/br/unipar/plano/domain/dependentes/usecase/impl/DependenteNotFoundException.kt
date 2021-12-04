package br.unipar.plano.domain.dependentes.usecase.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.dependentes.model.IdDependente

class DependenteNotFoundException(idDependente: IdDependente) : NotFoundException("Central com id ${idDependente.id} não encontrada")