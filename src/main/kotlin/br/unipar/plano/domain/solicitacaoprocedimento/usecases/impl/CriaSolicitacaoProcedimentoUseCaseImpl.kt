package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.usecases.CriaSolicitacaoProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import br.unipar.plano.domain.solicitacaoprocedimento.service.impl.ValidadorSolicitacaoProcedimento
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
//      validar carteirinha
        ValidadorSolicitacaoProcedimento.validarObrigatoridadePrestadorMedico(solicitacaoProcedimento);
        ValidadorSolicitacaoProcedimento.validarProcedimentoRestrito(
            solicitacaoProcedimento,
            solicitacaoProcedimentoRepository
        );
    }

}