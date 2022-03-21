package br.unipar.plano.interfaces.rest.solicitacaoprocedimento


import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.model.Procedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.SolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.StatusSolicitacaoProcedimento
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoDTO
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.NotNull

data class SolicitacaoProcedimentoSummaryDTO(
    val id: IdSolicitacaoProcedimento,
    val statusSolicitacao: StatusSolicitacaoProcedimento,
    val valorProcedimento: BigDecimal
) {
    companion object {
        fun toDTO(solicitacaoProcedimento: SolicitacaoProcedimento) = SolicitacaoProcedimentoSummaryDTO(
            id = solicitacaoProcedimento.id,
            statusSolicitacao = solicitacaoProcedimento.statusSolicitacao,
            valorProcedimento = solicitacaoProcedimento.procedimento.valor
        )
    }
}

data class SolicitacaoProcedimentoDetailsDTO(
    val id: IdSolicitacaoProcedimento,
    val statusSolicitacao: StatusSolicitacaoProcedimento,
    val solicitacaoProcedimentoDTO: SolicitacaoProcedimentoDTO
) {
    companion object {
        fun toDTO(solicitacaoProcedimento: SolicitacaoProcedimento) = SolicitacaoProcedimentoDetailsDTO(
            id = solicitacaoProcedimento.id,
            statusSolicitacao = solicitacaoProcedimento.statusSolicitacao,
            solicitacaoProcedimentoDTO = SolicitacaoProcedimentoDTO.toDTO(solicitacaoProcedimento)
        )
    }
}

data class SolicitacaoProcedimentoDTO(
    @field:NotNull
    val procedimento: ProcedimentoDTO,

    @field:NotNull
    val statusSolicitacao: StatusSolicitacaoProcedimento,

    @field:NotNull
    val dataCriacao: LocalDate,

    val dataLiberacaoRejeicao: LocalDate?,

    val descricaoRejeicao: String?
) {

    fun toModel(id: IdSolicitacaoProcedimento) = SolicitacaoProcedimento(
        id = id,
        procedimento = Procedimento(
            id = IdProcedimento(),
            dataEmissao = this.procedimento.dataEmissao,
            contrato = this.procedimento.contrato,
            prestador = this.procedimento.prestador,
            carteirinha = this.procedimento.carteirinha,
            valor = this.procedimento.valor,
            status = this.procedimento.status,
            dataCancelamento = this.procedimento.dataCancelamento,
            dataProcedimento =  this.procedimento.dataProcedimento,
            servico =  this.procedimento.servico,
            especialidade = this.procedimento.especialidade
        ),
        statusSolicitacao = this.statusSolicitacao,
        dataCriacao = this.dataCriacao,
        dataLiberacaoRejeicao = this.dataLiberacaoRejeicao,
        descricaoRejeicao = this.descricaoRejeicao
    )

    companion object {
        fun toDTO(solicitacaoProcedimento: SolicitacaoProcedimento) = SolicitacaoProcedimentoDTO(
            procedimento = ProcedimentoDTO(
//                id = solicitacaoProcedimento.procedimento.id,
                dataEmissao = solicitacaoProcedimento.procedimento.dataEmissao,
                contrato = solicitacaoProcedimento.procedimento.contrato,
                prestador = solicitacaoProcedimento.procedimento.prestador,
                carteirinha = solicitacaoProcedimento.procedimento.carteirinha,
                valor = solicitacaoProcedimento.procedimento.valor,
                status = solicitacaoProcedimento.procedimento.status,
                dataCancelamento = solicitacaoProcedimento.procedimento.dataCancelamento,
                dataProcedimento =  solicitacaoProcedimento.procedimento.dataProcedimento,
                servico =  solicitacaoProcedimento.procedimento.servico,
                especialidade = solicitacaoProcedimento.procedimento.especialidade
            ),
            statusSolicitacao = solicitacaoProcedimento.statusSolicitacao,
            dataCriacao = solicitacaoProcedimento.dataCriacao,
            dataLiberacaoRejeicao = solicitacaoProcedimento.dataLiberacaoRejeicao,
            descricaoRejeicao = solicitacaoProcedimento.descricaoRejeicao
        )
    }
}