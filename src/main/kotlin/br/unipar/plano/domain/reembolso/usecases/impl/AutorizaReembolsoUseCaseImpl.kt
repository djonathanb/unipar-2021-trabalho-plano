package br.unipar.plano.domain.reembolso.usecases.impl

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.repository.ReembolsoRepository
import br.unipar.plano.domain.reembolso.usecases.AutorizaReembolsoUseCase
import br.unipar.plano.domain.reembolso.usecases.exceptions.ReembolsoNotFoundException
import org.springframework.stereotype.Service

@Service
class AutorizaReembolsoUseCaseImpl(private val reembolsoRepository: ReembolsoRepository) : AutorizaReembolsoUseCase {
    override fun executa(idReembolso: IdReembolso) {
        val reembolso = reembolsoRepository.findById(idReembolso).orElseThrow { ReembolsoNotFoundException(idReembolso) }
        reembolsoRepository.save(reembolso.autoriza())
    }
}