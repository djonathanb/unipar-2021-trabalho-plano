package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.centrais.model.IdCentral

interface CredenciaCentralUseCase {

    fun executa(idCentral: IdCentral)

}
