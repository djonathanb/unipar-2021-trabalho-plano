package br.unipar.plano.domain.credenciamentos.usecases

import br.unipar.plano.domain.credenciamentos.model.Servico

interface CriaServicoUseCase {
    fun executa(servico: Servico): Servico
}