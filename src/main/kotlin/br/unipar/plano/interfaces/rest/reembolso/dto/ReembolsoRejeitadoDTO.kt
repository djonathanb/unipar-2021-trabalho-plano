package br.unipar.plano.interfaces.rest.reembolso.dto

import br.unipar.plano.domain.reembolso.model.*
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ReembolsoRejeitadoSummaryDTO(
        val id: IdRejeicao,
        val dataRejeicao: LocalDate,
        val motivo: String,
        val agente: Int,
        val reembolso: Reembolso
) {

    companion object {

        fun toDTO(rejeicaoReembolso: RejeicaoReembolso) = ReembolsoRejeitadoSummaryDTO(
                id = rejeicaoReembolso.id,
                dataRejeicao = rejeicaoReembolso.dataRejeicao,
                motivo = rejeicaoReembolso.motivo,
                agente = rejeicaoReembolso.agente,
                reembolso = rejeicaoReembolso.reembolso
        )

    }

}

data class ReembolsoRejeitadoDetailsDTO(
        val id: IdRejeicao,
        val dataRejeicao: LocalDate,
        val motivo: String,
        val agente: Int,
        val reembolso: Reembolso
) {

    companion object {

        fun toDTO(rejeicaoReembolso: RejeicaoReembolso) = ReembolsoRejeitadoDetailsDTO(
                id = rejeicaoReembolso.id,
                dataRejeicao = rejeicaoReembolso.dataRejeicao,
                motivo = rejeicaoReembolso.motivo,
                agente = rejeicaoReembolso.agente,
                reembolso = rejeicaoReembolso.reembolso
        )

    }

}

data class ReembolsoRejeitadoDTO (
        @field:NotNull
        val id: IdRejeicao,

        @field:NotNull
        val dataRejeicao: LocalDate,

        @field:NotBlank
        val motivo: String,

        @field:NotNull
        val agente: Int,

        @field:NotNull
        val reembolso: Reembolso
){

    fun toModel(id: IdRejeicao) = RejeicaoReembolso(
            id = id,
            dataRejeicao = dataRejeicao,
            motivo = motivo,
            agente = agente,
            reembolso = reembolso
    )

    companion object {

        fun toDTO(rejeicaoReembolso: RejeicaoReembolso) = ReembolsoRejeitadoDTO(
                id = rejeicaoReembolso.id,
                dataRejeicao = rejeicaoReembolso.dataRejeicao,
                motivo = rejeicaoReembolso.motivo,
                agente = rejeicaoReembolso.agente,
                reembolso = rejeicaoReembolso.reembolso
        )
    }

}