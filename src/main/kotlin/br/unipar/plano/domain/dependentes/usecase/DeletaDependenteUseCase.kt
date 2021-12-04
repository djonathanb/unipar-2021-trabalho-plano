package br.unipar.plano.domain.dependentes.usecase

import br.unipar.plano.domain.dependentes.model.IdDependente

interface DeletaDependenteUseCase {

    fun executa(idDependente: IdDependente)
}