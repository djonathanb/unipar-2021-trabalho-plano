package br.unipar.plano.domain.reembolso.usecases.impl

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.model.RejeicaoReembolso
import br.unipar.plano.domain.reembolso.repository.ReembolsoRejeitadoRepository
import br.unipar.plano.domain.reembolso.repository.ReembolsoRepository
import br.unipar.plano.domain.reembolso.usecases.RejeitaReembolsoUseCase
import br.unipar.plano.domain.reembolso.usecases.exceptions.ReembolsoNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RejeitaReembolsoUseCaseImpl(
        private val reembolsoRepository: ReembolsoRepository,
        private val reembolsoRejeitadoRepository: ReembolsoRejeitadoRepository
) : RejeitaReembolsoUseCase {
    override fun executa(idReembolso: IdReembolso, rejeicaoReembolso: RejeicaoReembolso) {
        val reembolso = reembolsoRepository.findById(idReembolso).orElseThrow {
            ReembolsoNotFoundException(idReembolso)
        }
        reembolso.dataAnalize = LocalDate.now()
        reembolso.rejeita(rejeicaoReembolso)

        reembolsoRepository.save(reembolso)
        reembolsoRejeitadoRepository.save(rejeicaoReembolso)
    }

}
