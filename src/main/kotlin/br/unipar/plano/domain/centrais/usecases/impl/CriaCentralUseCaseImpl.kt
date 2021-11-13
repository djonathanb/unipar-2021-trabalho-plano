package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.services.impl.centrais
import br.unipar.plano.domain.centrais.usecases.CriaCentralUseCase
import org.springframework.stereotype.Service

@Service
class CriaCentralUseCaseImpl : CriaCentralUseCase {

    override fun executa(central: Central): Central {
        centrais.add(central)
        return central
    }

}