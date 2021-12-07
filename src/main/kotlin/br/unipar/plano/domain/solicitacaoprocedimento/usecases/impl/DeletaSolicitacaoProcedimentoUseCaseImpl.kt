package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.usecases.DeletaSolicitacaoProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import org.springframework.stereotype.Service

@Service
class DeletaSolicitacaoProcedimentoUseCaseImpl(private val solicitacaoProcedimentoRepository: SolicitacaoProcedimentoRepository) : DeletaSolicitacaoProcedimentoUseCase {

    override fun executa(idSolicitacaoProcedimento: IdSolicitacaoProcedimento) {
        val central = solicitacaoProcedimentoRepository.findById(idSolicitacaoProcedimento).orElseThrow { SolicitacaoProcedimentoNotFoundException(idSolicitacaoProcedimento) }
        return solicitacaoProcedimentoRepository.delete(central)
    }
}