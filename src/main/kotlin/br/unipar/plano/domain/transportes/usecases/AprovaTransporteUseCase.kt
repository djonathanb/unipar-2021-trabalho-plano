package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.centrais.model.Transporte

interface AprovaTransporteUseCase {
    fun executa(transporte: Transporte): Transporte
}