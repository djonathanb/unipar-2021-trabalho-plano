package br.unipar.plano.domain.planos.usecases.impl

import br.unipar.plano.domain.planos.model.PlanoRepository
import br.unipar.plano.domain.planos.model.IdPlano
import br.unipar.plano.domain.planos.usecases.DeletaPlanoUseCase
import org.springframework.stereotype.Service

@Service
class DeletaPlanoUseCaseImpl(private val planoRepository: PlanoRepository) : DeletaPlanoUseCase {

    override fun executa(idPlano: IdPlano) {
        val plano = planoRepository.findById(idPlano).orElseThrow { PlanoNotFoundException(idPlano) }
        return planoRepository.delete(plano)
    }

}