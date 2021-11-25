package br.unipar.plano.domain.contratos.usecases

import br.unipar.plano.domain.centrais.model.IdContrato

interface DeletaContratosUseCase {

    fun executa(idContrato: IdContrato)
}