package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.usecases.CriaCentralUseCase
import org.springframework.stereotype.Service

@Service
class CriaCentralUseCaseImpl(private val centralRepository: CentralRepository) : CriaCentralUseCase {

    override fun executa(central: Central): Central {
        if (centralRepository.existsById(central.id)) {
            throw IllegalStateException("Outra Central com id ${central.id} já foi cadastrada")
        }
        return centralRepository.save(central)
    }

}