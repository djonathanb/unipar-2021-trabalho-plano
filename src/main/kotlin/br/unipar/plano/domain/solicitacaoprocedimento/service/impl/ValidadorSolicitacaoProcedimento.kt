package br.unipar.plano.domain.solicitacaoprocedimento.service.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import java.time.Duration

class ValidadorSolicitacaoProcedimento {

    companion object Factory {
        fun validarObrigatoridadePrestadorMedico(solicitacaoProcedimento: SolicitacaoProcedimento) {
            if (solicitacaoProcedimento.procedimento.prestador == null) {
                throw Exception("Não é possível salvar uma Solicitação de procedimento sem um médico responsável")
            }
        }

        fun validarProcedimentoRestrito(
            solicitacaoProcedimento: SolicitacaoProcedimento,
            solicitacaoProcedimentoRepository: SolicitacaoProcedimentoRepository
        ) {
            val solicitacoesProcedimentos =
                solicitacaoProcedimentoRepository.findByProcedimento_Id(solicitacaoProcedimento.procedimento.id);

            val solicitacoesEmAbertoParaDeterminadoUsuario = solicitacoesProcedimentos.filter { solicitacao ->
                solicitacao.statusSolicitacao.equals(StatusSolicitacaoProcedimento.ABERTO)
                        && solicitacao.procedimento.carteirinha.equals(solicitacaoProcedimento.procedimento.carteirinha)
                        && Duration.between(solicitacao.dataCriacao, solicitacaoProcedimento.dataCriacao)
                    .toDays() < 30
            };

            if (solicitacoesEmAbertoParaDeterminadoUsuario.isNotEmpty()) {
                throw Exception("Não é possível salvar mais uma Solicitação para esse procedimento restrito")
            }
        }
    }
}