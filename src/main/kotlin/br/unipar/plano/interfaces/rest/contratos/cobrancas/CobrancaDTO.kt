package br.unipar.plano.interfaces.rest.contratos.cobrancas

import br.unipar.plano.domain.contratos.cobrancas.model.*
import br.unipar.plano.domain.contratos.cobrancas.valueobjects.StatusCobranca


data class CobrancaSummaryDTO(
    val id: IdCobranca,
) {

    companion object {

        fun toDTO(cobranca: Cobranca) = CobrancaSummaryDTO(
            id = cobranca.id,
        )

    }

}

data class CobrancaDetailsDTO(
    val id: IdCobranca,
    val status: StatusCobranca,

) {

    companion object {

        fun toDTO(cobranca: Cobranca) = CobrancaDetailsDTO(
            id = cobranca.id,
            status = cobranca.status

        )

    }

}


