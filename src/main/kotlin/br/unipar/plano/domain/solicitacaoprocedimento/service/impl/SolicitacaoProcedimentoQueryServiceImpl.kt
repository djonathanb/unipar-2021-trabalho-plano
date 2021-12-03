package br.unipar.plano.domain.solicitacaoprocedimento.service.impl

import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Procedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoQueryService
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoRepository
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl.SolicitacaoProcedimentoNotFoundException
import org.springframework.stereotype.Service

@Service
class SolicitacaoProcedimentoQueryServiceImpl(private val solicitacaoProcedimentoRepository : SolicitacaoProcedimentoRepository): SolicitacaoProcedimentoQueryService {

    override fun lista(): List<SolicitacaoProcedimento> = solicitacaoProcedimentoRepository.findAll()

    override fun buscaPorId(idSolicitacaoProcedimento: IdSolicitacaoProcedimento): SolicitacaoProcedimento = solicitacaoProcedimentoRepository.findById(idSolicitacaoProcedimento).orElseThrow {
        SolicitacaoProcedimentoNotFoundException(idSolicitacaoProcedimento)
    }

    override fun findByProcedimentoAndStatusSolicitacaoEquals(
        procedimento: Procedimento,
        statusSolicitacao: StatusSolicitacaoProcedimento
    ): List<SolicitacaoProcedimento> {
        return solicitacaoProcedimentoRepository.findByProcedimentoAndStatusSolicitacaoEquals(procedimento, statusSolicitacao);
    }

    override fun findByProcedimento_Id(idProcedimento: IdProcedimento): List<SolicitacaoProcedimento> {
       return solicitacaoProcedimentoRepository.findByProcedimento_Id(idProcedimento);
    }
}