package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.centrais.model.Transporte

interface CancelaTransporteUseCase {
    fun executa(transporte: Transporte): Transporte
}