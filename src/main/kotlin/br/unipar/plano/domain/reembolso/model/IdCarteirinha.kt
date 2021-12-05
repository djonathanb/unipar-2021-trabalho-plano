package br.unipar.plano.domain.reembolso.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdCarteirinha(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable