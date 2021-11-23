package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.usecases.CredenciaCentralUseCase
import org.springframework.stereotype.Service

@Service
class CredenciaCentralUseCaseImpl(private val centralRepository: CentralRepository) : CredenciaCentralUseCase {

    override fun executa(idCentral: IdCentral) {
        val central = centralRepository.findById(idCentral).orElseThrow { CentralNotFoundException(idCentral) }
        centralRepository.save(central.credencia())
    }

}