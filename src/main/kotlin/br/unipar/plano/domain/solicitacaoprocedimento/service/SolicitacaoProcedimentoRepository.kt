package br.unipar.plano.domain.solicitacaoprocedimento.service

import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Procedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SolicitacaoProcedimentoRepository : JpaRepository<SolicitacaoProcedimento, IdSolicitacaoProcedimento> {

    fun findByProcedimentoAndStatusSolicitacaoEquals(
        procedimento: Procedimento,
        statusSolicitacao: StatusSolicitacaoProcedimento
    ): List<SolicitacaoProcedimento>;

    fun save(solicitacaoProcedimento: Optional<SolicitacaoProcedimento>)
    fun delete(idSolicitacaoProcedimento: IdSolicitacaoProcedimento)

    fun findByProcedimento_Id(idProcedimento: IdProcedimento): List<SolicitacaoProcedimento>
}