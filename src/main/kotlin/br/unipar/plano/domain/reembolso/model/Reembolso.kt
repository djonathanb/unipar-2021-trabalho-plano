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

        @Column
        val estadoSolicitacao: String,

        @Enumerated(EnumType.STRING)
        val status: StatusReembolso,

        @Column
        val valor: BigDecimal,

        @Column
        val data: LocalDate,

        @OneToOne(cascade = [CascadeType.ALL])
        val usuario: Usuario
){

        fun autoriza(): Reembolso {
                if (status == StatusReembolso.APROVADO) {
                        throw IllegalStateException("Não é possível autorizar um reembolso com status $status")
                }
                return copy(status = StatusReembolso.APROVADO)
        }

        fun rejeita(): Reembolso {
                if (status != StatusReembolso.APROVADO) {
                        throw IllegalStateException("Não é possível rejeitar um reembolso com status $status")
                }
                return copy(status = StatusReembolso.REJEITADO)
        }

        fun with(
                id: IdReembolso = this.id,
                estadoSolicitacao: String = this.estadoSolicitacao,
                valor: BigDecimal = this.valor,
                data: LocalDate = this.data,
                usuario: Usuario = this.usuario
        ) = copy(
                id = id,
                estadoSolicitacao = estadoSolicitacao,
                valor = valor,
                data = data,
                usuario = usuario
        )

        private fun copy(
                id: IdReembolso = this.id,
                estadoSolicitacao: String = this.estadoSolicitacao,
                valor: BigDecimal = this.valor,
                data: LocalDate = this.data,
                usuario: Usuario = this.usuario,
                status: StatusReembolso = this.status
        ) = Reembolso(
                id = id,
                estadoSolicitacao = estadoSolicitacao,
                valor = valor,
                data = data,
                usuario = usuario,
                status = status
        )

}