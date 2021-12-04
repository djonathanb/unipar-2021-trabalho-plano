package br.unipar.plano.domain.contratos.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdContrato(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable
