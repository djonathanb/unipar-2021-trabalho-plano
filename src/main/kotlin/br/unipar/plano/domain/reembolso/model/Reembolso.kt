package br.unipar.plano.domain.reembolso.model

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

enum class StatusReembolso {
    EM_ANALIZE, APROVADO, REJEITADO
}

@Entity
class Reembolso(
        @field:EmbeddedId
        val id: IdReembolso,

        @Column(nullable = false)
        val estadoSolicitacao: Estado,

        @Enumerated(EnumType.STRING)
        var statusReembolso: StatusReembolso = StatusReembolso.EM_ANALIZE,

        @Column(nullable = false)
        val valor: BigDecimal,

        @Column(nullable = false)
        val dataSolicitacao: LocalDate = LocalDate.now(),

        @Column(nullable = false)
        var dataAnalize: LocalDate,

        @OneToOne(cascade = [CascadeType.ALL])
        val usuario: Usuario,

        @OneToOne
        var rejeicaoReembolso: RejeicaoReembolso
){

        fun autoriza(): Reembolso {
                if (statusReembolso == StatusReembolso.APROVADO) {
                        throw IllegalStateException("Não é possível autorizar um reembolso com status $statusReembolso")
                }
                return copy(status = StatusReembolso.APROVADO)
        }

        fun rejeita(rejeicaoReembolso: RejeicaoReembolso): Reembolso {
                if (statusReembolso != StatusReembolso.EM_ANALIZE) {
                        throw IllegalStateException("Não é possível rejeitar um reembolso com status $statusReembolso")
                }

                this.rejeicaoReembolso = rejeicaoReembolso

                return copy(status = StatusReembolso.REJEITADO)
        }

        fun with(
                id: IdReembolso = this.id,
                estadoSolicitacao: Estado = this.estadoSolicitacao,
                valor: BigDecimal = this.valor,
                dataSolicitacao: LocalDate = this.dataSolicitacao,
                dataAnalize: LocalDate = this.dataAnalize,
                usuario: Usuario = this.usuario
        ) = copy(
                id = id,
                estadoSolicitacao = estadoSolicitacao,
                valor = valor,
                dataSolicitacao = dataSolicitacao,
                dataAnalize = dataAnalize,
                usuario = usuario
        )

        private fun copy(
                id: IdReembolso = this.id,
                estadoSolicitacao: Estado = this.estadoSolicitacao,
                valor: BigDecimal = this.valor,
                dataSolicitacao: LocalDate = this.dataSolicitacao,
                dataAnalize: LocalDate = this.dataAnalize,
                usuario: Usuario = this.usuario,
                status: StatusReembolso = this.statusReembolso,
                rejeicaoReembolso:RejeicaoReembolso = this.rejeicaoReembolso
        ) = Reembolso(
                id = id,
                estadoSolicitacao = estadoSolicitacao,
                valor = valor,
                dataSolicitacao = dataSolicitacao,
                dataAnalize = dataAnalize,
                usuario = usuario,
                statusReembolso = status,
                rejeicaoReembolso = rejeicaoReembolso
        )
}