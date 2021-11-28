package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.centrais.model.Transporte

interface CancelarTransporteUseCase {
    fun executa(transporte: Transporte): Transporte
}