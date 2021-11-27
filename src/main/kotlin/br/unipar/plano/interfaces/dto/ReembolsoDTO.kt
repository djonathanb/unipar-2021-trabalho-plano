package br.unipar.plano.interfaces.dto

import br.unipar.plano.domain.reembolso.model.*
import java.math.BigDecimal
import java.util.*
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ReembolsoSummaryDTO(
        val id: IdReembolso,
        val estadoSolicitacao: String,
        val status: StatusReembolso,
        val valor: BigDecimal,
        val data: Date,
        val usuario: Usuario
) {

    companion object {

        fun toDTO(reembolso: Reembolso) = ReembolsoSummaryDTO(
                id = reembolso.id,
                estadoSolicitacao = reembolso.estadoSolicitacao,
                status = reembolso.status,
                valor = reembolso.valor,
                data = reembolso.data,
                usuario = reembolso.usuario
        )

    }

}

data class ReembolsoDetailsDTO(
        val id: IdReembolso,
        val estadoSolicitacao: String,
        val status: StatusReembolso,
        val valor: BigDecimal,
        val data: Date,
        val usuario: Usuario
) {

    companion object {

        fun toDTO(reembolso: Reembolso) = ReembolsoDetailsDTO(
                id = reembolso.id,
                estadoSolicitacao = reembolso.estadoSolicitacao,
                status = reembolso.status,
                valor = reembolso.valor,
                data = reembolso.data,
                usuario = reembolso.usuario
        )

    }

}

data class ReembolsoDTO(
        @field:NotBlank
        val estadoSolicitacao: String,

        @field:Enumerated(EnumType.STRING)
        val status: StatusReembolso,

        @field:NotNull
        val valor: BigDecimal,

        @field:NotNull
        val data: Date,

        @field:NotNull
        val usuario: Usuario
) {

    fun toModel(id: IdReembolso) = Reembolso(
            id = id,
            estadoSolicitacao = estadoSolicitacao,
            status = status,
            valor = valor,
            data = data,
            usuario = usuario
    )

    companion object {

        fun toDTO(reembolso: Reembolso) = ReembolsoDTO(
                estadoSolicitacao = reembolso.estadoSolicitacao,
                status = reembolso.status,
                valor = reembolso.valor,
                data = reembolso.data,
                usuario = reembolso.usuario
        )
    }

}