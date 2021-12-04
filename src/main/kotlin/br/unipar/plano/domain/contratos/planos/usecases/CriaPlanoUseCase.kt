package br.unipar.plano.domain.contratos.planos.usecases

import br.unipar.plano.domain.contratos.planos.model.Plano

interface CriaPlanoUseCase {
    fun executa(plano: Plano): Plano
}