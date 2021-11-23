package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.centrais.model.IdCentral

interface DescredenciaCentralUseCase {

    fun executa(idCentral: IdCentral)

}
