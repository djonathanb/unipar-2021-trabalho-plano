package br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico

interface CriaPrestMedicoUseCase {
    fun executa(prestadorMedico: PrestadorMedico): PrestadorMedico
}