package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.usecases.AtualizaCentralUseCase
import org.springframework.stereotype.Service

@Service
class AtualizaCentralUseCaseImpl(private val centralRepository: CentralRepository) : AtualizaCentralUseCase {

    override fun executa(idCentral: IdCentral, transformation: (Central) -> Central) {
        val central = centralRepository.findById(idCentral).orElseThrow { CentralNotFoundException(idCentral) }
        centralRepository.save(transformation(central).with(id = idCentral))
    }

}