package br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl

import br.unipar.plano.domain.centrais.usecases.impl.SolicitacaoProcedimentoNotFoundException
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.AtualizaSolicitacaoProcedimentoUseCase
import org.springframework.stereotype.Service

@Service
class AtualizaSolicitacaoProcedimentoUseCaseImpl(private val solicitacaoProcedimentoRepository: SolicitacaoProcedimentoRepository): AtualizaSolicitacaoProcedimentoUseCase {

    override fun executa(
        idSolicitacaoProcedimento: IdSolicitacaoProcedimento,
        transformation: (SolicitacaoProcedimento) -> SolicitacaoProcedimento
    ) {
        val central = solicitacaoProcedimentoRepository.findById(idSolicitacaoProcedimento).orElseThrow { SolicitacaoProcedimentoNotFoundException(idSolicitacaoProcedimento) }
        solicitacaoProcedimentoRepository.save(transformation(central).with(id = idSolicitacaoProcedimento))
    }
}