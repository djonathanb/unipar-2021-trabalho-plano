package br.unipar.plano.domain.solicitacaoprocedimento.service.impl

import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoDTO
import java.time.Duration

class ValidadorSolicitacaoProcedimento {

    companion object Factory {
        fun validarObrigatoridadePrestadorMedico(solicitacaoProcedimento: SolicitacaoProcedimentoDTO) {
            if (solicitacaoProcedimento.procedimento.prestador == null) {
                throw Exception("Não é possível salvar uma Solicitação de procedimento sem um médico responsável")
            }
        }

        fun validarProcedimentoRestrito(
            solicitacaoProcedimentoDTO: SolicitacaoProcedimentoDTO,
            solicitacaoProcedimentoRepository: SolicitacaoProcedimentoRepository
        ) {
            val solicitacaoProcedimento =
                solicitacaoProcedimentoRepository.findByProcedimento_Id(solicitacaoProcedimentoDTO.procedimento.id);

            val solicitacoesEmAbertoParaDeterminadoUsuario = solicitacaoProcedimento.filter { solicitacao ->
                solicitacao.statusSolicitacao.equals(StatusSolicitacaoProcedimento.ABERTO)
                        && solicitacao.procedimento.carteirinha.equals(solicitacaoProcedimentoDTO.procedimento.carteirinha)
                        && Duration.between(solicitacao.dataCriacao, solicitacaoProcedimentoDTO.dataCriacao)
                    .toDays() < 30
            };

            if (solicitacoesEmAbertoParaDeterminadoUsuario.isNotEmpty()) {
                throw Exception("Não é possível salvar mais uma Solicitação para esse procedimento restrito")
            }
        }
    }
}