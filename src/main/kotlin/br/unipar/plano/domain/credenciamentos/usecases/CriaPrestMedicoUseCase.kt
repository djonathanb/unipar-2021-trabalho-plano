package br.unipar.plano.domain.credenciamentos.usecases

import br.unipar.plano.domain.credenciamentos.model.PrestadorMedico

interface CriaPrestMedicoUseCase {
    fun executa(prestadorMedico: PrestadorMedico): PrestadorMedico
}