package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.centrais.model.Central

interface CriaCentralUseCase {
    fun executa(central: Central): Central
}