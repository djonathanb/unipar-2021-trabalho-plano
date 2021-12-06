package br.unipar.plano.domain.reembolso.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*

data class IdAgente(@field:JsonValue var id: UUID = UUID.randomUUID()): Serializable