package br.unipar.plano.domain.contratos.cobrancas.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdCobranca(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable
