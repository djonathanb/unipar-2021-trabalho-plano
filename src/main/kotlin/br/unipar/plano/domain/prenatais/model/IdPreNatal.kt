package br.unipar.plano.domain.prenatal.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdPreNatal(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable