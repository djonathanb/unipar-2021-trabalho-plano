package br.unipar.plano.domain.procedimento.usecases.impl

import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.ProcedimentoRepository
import br.unipar.plano.domain.procedimento.model.StatusProcedimento
import br.unipar.plano.domain.procedimento.usecases.CancelaProcedimentoUseCase
import org.springframework.stereotype.Service

@Service
class CancelaProcedimentoUseCaseImpl(private val procedimentoRepository: ProcedimentoRepository) : CancelaProcedimentoUseCase {

    override fun executa(idProcedimento: IdProcedimento) {
        val procedimento = procedimentoRepository.findById(idProcedimento).orElseThrow { ProcedimentoNotFoundException(idProcedimento) }
        return procedimentoRepository.delete(procedimento)
    }
}