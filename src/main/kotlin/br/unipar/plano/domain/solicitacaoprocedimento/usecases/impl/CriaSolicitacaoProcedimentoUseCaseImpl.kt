package br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import br.unipar.plano.domain.solicitacaoprocedimento.service.impl.ValidadorSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.CriaSolicitacaoProcedimentoUseCase
import org.springframework.stereotype.Service

@Service
class CriaSolicitacaoProcedimentoUseCaseImpl(private val solicitacaoProcedimentoRepository: SolicitacaoProcedimentoRepository) : CriaSolicitacaoProcedimentoUseCase {

    override fun executa(solicitacaoProcedimento: SolicitacaoProcedimento): SolicitacaoProcedimento {
        validate(solicitacaoProcedimento);
        if (solicitacaoProcedimentoRepository.existsById(solicitacaoProcedimento.id)) {
            throw IllegalStateException("Outra Solicitacao com id ${solicitacaoProcedimento.id} já foi cadastrada")
        }
        return solicitacaoProcedimentoRepository.save(solicitacaoProcedimento);
    }

    private fun validate(solicitacaoProcedimento: SolicitacaoProcedimento) {
        ValidadorSolicitacaoProcedimento.validarObrigatoridadePrestadorMedico(solicitacaoProcedimento);
        ValidadorSolicitacaoProcedimento.validarProcedimentoRestrito(
            solicitacaoProcedimento,
            solicitacaoProcedimentoRepository
        );
    }

}