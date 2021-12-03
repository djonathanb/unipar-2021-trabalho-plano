package br.unipar.plano.domain.reembolso.model

import org.springframework.core.convert.converter.Converter
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import javax.persistence.*

enum class StatusReembolso {
    EM_ANALIZE, APROVADO, REJEITADO
}

@Entity
class Reembolso(
        @field:EmbeddedId
        val id: IdReembolso,

        @Column(nullable = false)
        val estadoSolicitacao: EnumEstados,

        @Enumerated(EnumType.STRING)
        val statusReembolso: StatusReembolso = StatusReembolso.EM_ANALIZE,

        @Column(nullable = false)
        val valor: BigDecimal,

        @Column(nullable = false)
        val dataSolicitacao: LocalDate = LocalDate.now(),

        @Column(nullable = true)
        val dataAnalize: LocalDate,

        @OneToOne(cascade = [CascadeType.ALL])
        val usuario: Usuario
){

        fun autoriza(): Reembolso {
                if (statusReembolso == StatusReembolso.APROVADO) {
                        throw IllegalStateException("Não é possível autorizar um reembolso com status $statusReembolso")
                }
                return copy(status = StatusReembolso.APROVADO)
        }

        fun rejeita(): Reembolso {
                if (statusReembolso != StatusReembolso.APROVADO) {
                        throw IllegalStateException("Não é possível rejeitar um reembolso com status $statusReembolso")
                }
                return copy(status = StatusReembolso.REJEITADO)
        }

        fun with(
                id: IdReembolso = this.id,
                estadoSolicitacao: EnumEstados = this.estadoSolicitacao,
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
                estadoSolicitacao: EnumEstados = this.estadoSolicitacao,
                valor: BigDecimal = this.valor,
                dataSolicitacao: LocalDate = this.dataSolicitacao,
                dataAnalize: LocalDate = this.dataAnalize,
                usuario: Usuario = this.usuario,
                status: StatusReembolso = this.statusReembolso
        ) = Reembolso(
                id = id,
                estadoSolicitacao = estadoSolicitacao,
                valor = valor,
                dataSolicitacao = dataSolicitacao,
                dataAnalize = dataAnalize,
                usuario = usuario,
                statusReembolso = status
        )
}