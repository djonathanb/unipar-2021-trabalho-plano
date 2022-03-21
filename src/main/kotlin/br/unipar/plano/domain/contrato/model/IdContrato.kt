package br.unipar.plano.domain.contrato.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdContrato(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable

/* Classe criada simplesmente com a finalidade de facilitar o desenvolvimento
 * Visto que a implementação da mesma depende de outra equipe */