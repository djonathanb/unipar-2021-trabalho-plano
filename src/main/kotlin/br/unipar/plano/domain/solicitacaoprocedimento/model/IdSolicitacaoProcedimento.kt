package br.unipar.plano.domain.solicitacaoprocedimento.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdSolicitacaoProcedimento(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable
