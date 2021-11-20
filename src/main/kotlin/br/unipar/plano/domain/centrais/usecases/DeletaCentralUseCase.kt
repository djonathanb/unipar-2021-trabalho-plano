package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.centrais.model.IdCentral

interface DeletaCentralUseCase {

    fun executa(idCentral: IdCentral)

}
