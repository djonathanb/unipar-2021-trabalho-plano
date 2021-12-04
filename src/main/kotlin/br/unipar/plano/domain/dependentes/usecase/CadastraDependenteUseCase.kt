package br.unipar.plano.domain.dependentes.usecase

import br.unipar.plano.domain.dependentes.model.Dependente

interface CadastraDependenteUseCase {
    fun cadastra(dependente: Dependente) : Dependente
}