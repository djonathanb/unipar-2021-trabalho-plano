package br.unipar.plano.domain.planos.usecases.impl

import br.unipar.plano.domain.planos.model.Plano
import br.unipar.plano.domain.planos.model.PlanoRepository
import br.unipar.plano.domain.planos.usecases.CriaPlanoUseCase
import org.springframework.stereotype.Service

@Service
class CriaPlanoUseCaseImpl(private val planoRepository: PlanoRepository) : CriaPlanoUseCase {

    override fun executa(plano: Plano): Plano {
        if (planoRepository.existsById(plano.id)) {
            throw IllegalStateException("Outro plano com id ${plano.id} já foi cadastrado")
        }
        return planoRepository.save(plano)
    }

}