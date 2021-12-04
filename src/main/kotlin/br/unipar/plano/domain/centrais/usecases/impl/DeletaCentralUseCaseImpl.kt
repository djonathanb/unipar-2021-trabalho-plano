package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.usecases.DeletaCentralUseCase
import org.springframework.stereotype.Service

@Service
class DeletaCentralUseCaseImpl(private val centralRepository: CentralRepository) : DeletaCentralUseCase {

    override fun executa(idCentral: IdCentral) {
        val central = centralRepository.findById(idCentral).orElseThrow { CentralNotFoundException(idCentral) }
        return centralRepository.delete(central)
    }

}