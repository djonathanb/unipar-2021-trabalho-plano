package br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.DeletaSolicitacaoProcedimentoUseCase
import org.springframework.stereotype.Service

@Service
class DeletaSolicitacaoProcedimentoUseCaseImpl(private val solicitacaoProcedimentoRepository: SolicitacaoProcedimentoRepository) : DeletaSolicitacaoProcedimentoUseCase {

    override fun executa(idSolicitacaoProcedimento: IdSolicitacaoProcedimento) {
        val central = solicitacaoProcedimentoRepository.findById(idSolicitacaoProcedimento).orElseThrow { SolicitacaoProcedimentoNotFoundException(idSolicitacaoProcedimento) }
        return solicitacaoProcedimentoRepository.delete(central)
    }
}