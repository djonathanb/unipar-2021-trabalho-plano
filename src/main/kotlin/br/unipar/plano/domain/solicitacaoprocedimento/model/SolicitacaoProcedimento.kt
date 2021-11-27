package br.unipar.plano.domain.solicitacaoprocedimento.model

import br.unipar.plano.domain.procedimento.model.Procedimento
import java.time.LocalDate
import javax.persistence.*

@Entity
class SolicitacaoProcedimento(
    @field:EmbeddedId
    val id: IdSolicitacaoProcedimento,

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "id_procedimento")
    val procedimento: Procedimento,

    @Column(nullable = false)
    var statusSolicitacao: StatusSolicitacaoProcedimento = StatusSolicitacaoProcedimento.ABERTO,

    @Column(name = "dataCriacao")
    @Temporal(TemporalType.DATE)
    val dataCriacao: LocalDate,

    @Column(name = "dataLiberacaoRejeicao")
    @Temporal(TemporalType.DATE)
    var dataLiberacaoRejeicao: LocalDate,

    @Column(name = "descricao_rejeicao")
    var descricaoRejeicao: String
) {

    fun with(
        id: IdSolicitacaoProcedimento = this.id,
        procedimento: Procedimento = this.procedimento,
        statusSolicitacao: StatusSolicitacaoProcedimento = this.statusSolicitacao,
        dataCriacao: LocalDate = this.dataCriacao,
        dataLiberacaoRejeicao: LocalDate = this.dataLiberacaoRejeicao,
        descricaoRejeicao: String = this.descricaoRejeicao
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
        dataLiberacaoRejeicao: LocalDate = this.dataLiberacaoRejeicao,
        descricaoRejeicao: String = this.descricaoRejeicao
    ) = SolicitacaoProcedimento(
        id = id,
        procedimento = procedimento,
        statusSolicitacao = statusSolicitacao,
        dataCriacao = dataCriacao,
        dataLiberacaoRejeicao = dataLiberacaoRejeicao,
        descricaoRejeicao = descricaoRejeicao
    )
}