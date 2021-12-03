package br.unipar.plano.domain.solicitacaoprocedimento.model

import br.unipar.plano.domain.procedimento.model.Procedimento
import java.time.LocalDate
import javax.persistence.*

@Entity
class SolicitacaoProcedimento(
    @field:EmbeddedId
    val id: IdSolicitacaoProcedimento,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_procedimento")
    val procedimento: Procedimento,

    @Column(name = "statusSolicitacao", nullable = false)
    var statusSolicitacao: StatusSolicitacaoProcedimento = StatusSolicitacaoProcedimento.ABERTO,

    @Column(name = "dataCriacao", nullable = false)
    val dataCriacao: LocalDate,

    @Column(name = "dataLiberacaoRejeicao")
    var dataLiberacaoRejeicao: LocalDate?,

    @Column(name = "descricao_rejeicao")
    var descricaoRejeicao: String?
) {

    fun with(
        id: IdSolicitacaoProcedimento = this.id,
        procedimento: Procedimento = this.procedimento,
        statusSolicitacao: StatusSolicitacaoProcedimento = this.statusSolicitacao,
        dataCriacao: LocalDate = this.dataCriacao,
        dataLiberacaoRejeicao: LocalDate? = this.dataLiberacaoRejeicao,
        descricaoRejeicao: String? = this.descricaoRejeicao
    ) = copy(
        id = id,
        procedimento = procedimento,
        statusSolicitacao = statusSolicitacao,
        dataCriacao = dataCriacao,
        dataLiberacaoRejeicao = dataLiberacaoRejeicao,
        descricaoRejeicao = descricaoRejeicao
    )

    private fun copy(
        id: IdSolicitacaoProcedimento = this.id,
        procedimento: Procedimento = this.procedimento,
        statusSolicitacao: StatusSolicitacaoProcedimento = this.statusSolicitacao,
        dataCriacao: LocalDate = this.dataCriacao,
        dataLiberacaoRejeicao: LocalDate? = this.dataLiberacaoRejeicao,
        descricaoRejeicao: String? = this.descricaoRejeicao
    ) = SolicitacaoProcedimento(
        id = id,
        procedimento = procedimento,
        statusSolicitacao = statusSolicitacao,
        dataCriacao = dataCriacao,
        dataLiberacaoRejeicao = dataLiberacaoRejeicao,
        descricaoRejeicao = descricaoRejeicao
    )
}