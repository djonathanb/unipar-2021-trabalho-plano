package br.unipar.plano.interfaces.rest.reembolso.dto

import br.unipar.plano.domain.reembolso.model.*
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ReembolsoRejeitadoSummaryDTO(
        val id: UUID,
        val dataRejeicao: LocalDate,
        val motivo: String,
        val agente: Int,
        val reembolso: Reembolso
) {

    companion object {

        fun toDTO(reembolsoRejeitado: ReembolsoRejeitado) = ReembolsoRejeitadoSummaryDTO(
                id = reembolsoRejeitado.id,
                dataRejeicao = reembolsoRejeitado.dataRejeicao,
                motivo = reembolsoRejeitado.motivo,
                agente = reembolsoRejeitado.agente,
                reembolso = reembolsoRejeitado.reembolso
        )

    }

}

data class ReembolsoRejeitadoDetailsDTO(
        val id: UUID,
        val dataRejeicao: LocalDate,
        val motivo: String,
        val agente: Int,
        val reembolso: Reembolso
) {

    companion object {

        fun toDTO(reembolsoRejeitado: ReembolsoRejeitado) = ReembolsoRejeitadoDetailsDTO(
                id = reembolsoRejeitado.id,
                dataRejeicao = reembolsoRejeitado.dataRejeicao,
                motivo = reembolsoRejeitado.motivo,
                agente = reembolsoRejeitado.agente,
                reembolso = reembolsoRejeitado.reembolso
        )

    }

}

data class ReembolsoRejeitadoDTO (
        @field:NotNull
        val id: UUID,

        @field:NotNull
        val dataRejeicao: LocalDate,

        @field:NotBlank
        val motivo: String,

        @field:NotNull
        val agente: Int,

        @field:NotNull
        val reembolso: Reembolso
){

    fun toModel(id: UUID) = ReembolsoRejeitado(
            id = id,
            dataRejeicao = dataRejeicao,
            motivo = motivo,
            agente = agente,
            reembolso = reembolso
    )

    companion object {

        fun toDTO(reembolsoRejeitado: ReembolsoRejeitado) = ReembolsoRejeitadoDTO(
                id = reembolsoRejeitado.id,
                dataRejeicao = reembolsoRejeitado.dataRejeicao,
                motivo = reembolsoRejeitado.motivo,
                agente = reembolsoRejeitado.agente,
                reembolso = reembolsoRejeitado.reembolso
        )
    }

}