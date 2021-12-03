package br.unipar.plano.domain.solicitacaoprocedimento.service.impl;

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoQueryService
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoService
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.CriaSolicitacaoProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.DeletaSolicitacaoProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.LiberaSolicitacaoProcedimentoUseCase
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.RejeitaSolicitacaoProcedimentoUseCase
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoDTO
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoDetailsDTO
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.SolicitacaoProcedimentoSummaryDTO
import org.springframework.stereotype.Service

@Service
public class SolicitacaoProcedimentoServiceImpl(
    private val liberaSolicitacaoProcedimentoUseCase: LiberaSolicitacaoProcedimentoUseCase,
    private val rejeitaSolicitacaoProcedimentoUseCase: RejeitaSolicitacaoProcedimentoUseCase,
    private val deletaSolicitacaoProcedimentoUseCase: DeletaSolicitacaoProcedimentoUseCase,
    private val criaSolicitacaoProcedimentoUseCase: CriaSolicitacaoProcedimentoUseCase,
    private val solicitacaoProcedimentoQueryService: SolicitacaoProcedimentoQueryService
) : SolicitacaoProcedimentoService {

    override fun insert(dto: SolicitacaoProcedimentoDTO): IdSolicitacaoProcedimento {
        val solicitacaoProcedimento = dto.toModel(IdSolicitacaoProcedimento());
        val novaSolicitacao = criaSolicitacaoProcedimentoUseCase.executa(solicitacaoProcedimento);
        return novaSolicitacao.id
    }

    override fun lista(): List<SolicitacaoProcedimentoSummaryDTO> = solicitacaoProcedimentoQueryService
        .lista().map(SolicitacaoProcedimentoSummaryDTO::toDTO);


    override fun buscaPorId(idSolicitacaoProcedimento: IdSolicitacaoProcedimento): SolicitacaoProcedimentoDetailsDTO {
        val solicitacaoProcedimento = solicitacaoProcedimentoQueryService.buscaPorId(idSolicitacaoProcedimento)
        return SolicitacaoProcedimentoDetailsDTO.toDTO(solicitacaoProcedimento);
    }

    override fun deleta(idSolicitacaoProcedimento: IdSolicitacaoProcedimento) {
        deletaSolicitacaoProcedimentoUseCase.executa(idSolicitacaoProcedimento);
    }

    override fun liberarSolicitacao(solicitacaoID: IdSolicitacaoProcedimento) {
        liberaSolicitacaoProcedimentoUseCase.executa(solicitacaoID);
    }

    override fun rejeitarSolicitacao(solicitacaoID: IdSolicitacaoProcedimento, descricaoRejeicao: String) {
        rejeitaSolicitacaoProcedimentoUseCase.executa(solicitacaoID, descricaoRejeicao);
    }
}