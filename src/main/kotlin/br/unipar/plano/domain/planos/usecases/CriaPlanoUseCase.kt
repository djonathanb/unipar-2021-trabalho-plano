package br.unipar.plano.domain.planos.usecases

import br.unipar.plano.domain.planos.model.Plano

interface CriaPlanoUseCase {
    fun executa(plano: Plano): Plano
}