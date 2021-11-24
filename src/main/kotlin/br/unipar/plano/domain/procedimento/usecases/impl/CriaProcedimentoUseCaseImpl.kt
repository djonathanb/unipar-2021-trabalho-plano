package br.unipar.plano.domain.procedimento.usecases.impl

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.usecases.CriaCentralUseCase
import br.unipar.plano.domain.procedimento.model.Procedimento
import br.unipar.plano.domain.procedimento.model.ProcedimentoRepository
import br.unipar.plano.domain.procedimento.usecases.CriaProcedimentoUseCase
import org.springframework.stereotype.Service

@Service
class CriaProcedimentoUseCaseImpl(private val procedimentoRepository: ProcedimentoRepository) : CriaProcedimentoUseCase {

    override fun executa(procedimento: Procedimento): Procedimento {
        if (procedimentoRepository.existsById(procedimento.id)) {
            throw IllegalStateException("Outr procedimento com id ${procedimento.id} já foi cadastrado")
        }
        return procedimentoRepository.save(procedimento)
    }

}