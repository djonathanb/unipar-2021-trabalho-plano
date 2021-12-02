package br.unipar.plano.domain.contratos.planos.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdPlano(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable
