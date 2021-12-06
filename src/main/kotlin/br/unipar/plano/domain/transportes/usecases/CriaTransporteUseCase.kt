package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.transportes.model.Transporte

interface CriaTransporteUseCase {
    fun executa(transporte: Transporte): Transporte
}