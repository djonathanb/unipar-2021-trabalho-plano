package br.unipar.plano.domain.procedimento.usecases.impl

import br.unipar.plano.domain.carteirinha.model.StatusCarteirinha
import br.unipar.plano.domain.procedimento.model.Especialidade
import br.unipar.plano.domain.procedimento.model.Procedimento
import br.unipar.plano.domain.procedimento.model.ProcedimentoRepository
import br.unipar.plano.domain.procedimento.usecases.CriaProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoQueryService
import org.springframework.stereotype.Service

@Service
class CriaProcedimentoUseCaseImpl(private val procedimentoRepository: ProcedimentoRepository,
                                  private val solicitacaoProcedimentoQueryService: SolicitacaoProcedimentoQueryService) : CriaProcedimentoUseCase {

    override fun executa(procedimento: Procedimento): Procedimento {
        return procedimentoRepository.save(procedimento)
    }
}