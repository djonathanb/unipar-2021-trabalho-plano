package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.usecases.DescredenciaCentralUseCase
import org.springframework.stereotype.Service

@Service
class DescredenciaCentralUseCaseImpl(private val centralRepository: CentralRepository) : DescredenciaCentralUseCase {

    override fun executa(idCentral: IdCentral) {
        val central = centralRepository.findById(idCentral).orElseThrow { CentralNotFoundException(idCentral) }
        centralRepository.save(central.descredencia())
    }

}