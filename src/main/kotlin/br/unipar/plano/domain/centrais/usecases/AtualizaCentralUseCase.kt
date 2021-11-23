package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.IdCentral

interface AtualizaCentralUseCase {

    fun executa(idCentral: IdCentral, transformation: (Central) -> Central)

}
