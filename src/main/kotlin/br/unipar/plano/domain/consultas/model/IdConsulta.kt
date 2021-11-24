
package br.unipar.plano.domain.consultas.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdConsulta(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable