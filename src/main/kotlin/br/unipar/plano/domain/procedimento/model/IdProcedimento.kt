package br.unipar.plano.domain.procedimento.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdProcedimento(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable
