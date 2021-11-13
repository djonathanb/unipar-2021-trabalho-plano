package br.unipar.plano.domain.centrais.model

import com.fasterxml.jackson.annotation.JsonValue
import java.util.*

data class IdCentral(
    @field:JsonValue val id: UUID = UUID.randomUUID()
)
