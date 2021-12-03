package br.unipar.plano.domain.reembolso.usecases.impl

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.model.ReembolsoRejeitado
import br.unipar.plano.domain.reembolso.repository.ReembolsoRejeitadoRepository
import br.unipar.plano.domain.reembolso.repository.ReembolsoRepository
import br.unipar.plano.domain.reembolso.usecases.RejeitaReembolsoUseCase
import br.unipar.plano.domain.reembolso.usecases.exceptions.ReembolsoNotFoundException
import org.springframework.stereotype.Service

@Service
class RejeitaReembolsoUseCaseImpl(
        private val reembolsoRepository: ReembolsoRepository,
        private val reembolsoRejeitadoRepository: ReembolsoRejeitadoRepository
) : RejeitaReembolsoUseCase {
    override fun executa(idReembolso: IdReembolso, reembolsoRejeitado: ReembolsoRejeitado) {
        val reembolso = reembolsoRepository.findById(idReembolso).orElseThrow {
            ReembolsoNotFoundException(idReembolso)
        }
        reembolso.rejeita()

        reembolsoRepository.save(reembolso)
        reembolsoRejeitadoRepository.save(reembolsoRejeitado)
    }

}
