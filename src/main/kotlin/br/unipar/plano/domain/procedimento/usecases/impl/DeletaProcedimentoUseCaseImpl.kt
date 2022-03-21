package br.unipar.plano.domain.procedimento.usecases.impl

import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.usecases.DeletaCentralUseCase
import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Procedimento
import br.unipar.plano.domain.procedimento.model.ProcedimentoRepository
import br.unipar.plano.domain.procedimento.usecases.DeletaProcedimentoUseCase
import org.springframework.stereotype.Service

@Service
class DeletaProcedimentoUseCaseImpl(private val procedimentoRepository: ProcedimentoRepository) : DeletaProcedimentoUseCase {

    override fun executa(idProcedimento: IdProcedimento) {
        val procedimento = procedimentoRepository.findById(idProcedimento).orElseThrow { ProcedimentoNotFoundException(idProcedimento) }
        return procedimentoRepository.delete(procedimento)
    }
}