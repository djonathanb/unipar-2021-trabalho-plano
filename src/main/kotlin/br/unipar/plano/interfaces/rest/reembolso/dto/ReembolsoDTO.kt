package br.unipar.plano.interfaces.rest.reembolso.dto

import br.unipar.plano.domain.reembolso.model.*
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ReembolsoSummaryDTO(
    val id: IdReembolso,
    val estadoSolicitacao: Estado,
    val statusReembolso: StatusReembolso,
    val valor: BigDecimal,
    val dataSolicitacao: LocalDate,
    val dataAnalize: LocalDate,
    val usuario: Usuario
) {

    companion object {

        fun toDTO(reembolso: Reembolso) = ReembolsoSummaryDTO(
                id = reembolso.id,
                estadoSolicitacao = reembolso.estadoSolicitacao,
                statusReembolso = reembolso.statusReembolso,
                valor = reembolso.valor,
                dataSolicitacao = reembolso.dataSolicitacao,
                dataAnalize = reembolso.dataAnalize,
                usuario = reembolso.usuario
        )

    }

}

data class ReembolsoDetailsDTO(
    val id: IdReembolso,
    val estadoSolicitacao: Estado,
    val statusReembolso: StatusReembolso,
    val valor: BigDecimal,
    val dataSolicitacao: LocalDate,
    val dataAnalize: LocalDate,
    val usuario: Usuario
) {

    companion object {

        fun toDTO(reembolso: Reembolso) = ReembolsoDetailsDTO(
                id = reembolso.id,
                estadoSolicitacao = reembolso.estadoSolicitacao,
                statusReembolso = reembolso.statusReembolso,
                valor = reembolso.valor,
                dataSolicitacao = reembolso.dataSolicitacao,
                dataAnalize = reembolso.dataAnalize,
                usuario = reembolso.usuario
        )

    }

}

data class ReembolsoDTO(

    @field:NotBlank
    val estadoSolicitacao: Estado,

    @field:Enumerated(EnumType.STRING)
    val statusReembolso: StatusReembolso,

    @field:NotNull
    val valor: BigDecimal,

    @field:NotNull
    val dataSolicitacao: LocalDate,

    @field:NotNull
    val dataAnalize: LocalDate,

    @field:NotNull
    val usuario: Usuario,

    val rejeicaoReembolso: RejeicaoReembolso
) {

    fun toModel(id: IdReembolso) = Reembolso(
            id = id,
            estadoSolicitacao = estadoSolicitacao,
            statusReembolso = statusReembolso,
            valor = valor,
            dataSolicitacao = dataSolicitacao,
            dataAnalize = dataAnalize,
            usuario = usuario,
            rejeicaoReembolso = rejeicaoReembolso
    )

    companion object {

        fun toDTO(reembolso: Reembolso) = ReembolsoDTO(
                estadoSolicitacao = reembolso.estadoSolicitacao,
                statusReembolso = reembolso.statusReembolso,
                valor = reembolso.valor,
                dataSolicitacao = reembolso.dataSolicitacao,
                dataAnalize = reembolso.dataAnalize,
                usuario = reembolso.usuario,
                rejeicaoReembolso = reembolso.rejeicaoReembolso
        )
    }

}