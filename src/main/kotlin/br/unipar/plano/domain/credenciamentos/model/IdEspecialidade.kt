package br.unipar.plano.domain.credenciamentos.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdEspecialidade(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable