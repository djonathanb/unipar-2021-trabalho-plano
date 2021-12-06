package br.unipar.plano.domain.procedimento.usecases.impl

import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Procedimento
import br.unipar.plano.domain.procedimento.model.ProcedimentoRepository
import br.unipar.plano.domain.procedimento.usecases.AtualizaProcedimentoUseCase
import br.unipar.plano.domain.procedimento.usecases.impl.ProcedimentoNotFoundException
import org.springframework.stereotype.Service

@Service
class AtualizaProcedimentoUseCaseImpl(private val procedimentoRepository: ProcedimentoRepository) : AtualizaProcedimentoUseCase {

    override fun executa(idProcedimento: IdProcedimento, transformation: (Procedimento) -> Procedimento) {
        val procedimento = procedimentoRepository.findById(idProcedimento).orElseThrow { ProcedimentoNotFoundException(idProcedimento) }
        procedimentoRepository.save(transformation(procedimento).with(id = idProcedimento))
    }

}