package br.unipar.plano.domain.centrais.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdTransporte(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable
