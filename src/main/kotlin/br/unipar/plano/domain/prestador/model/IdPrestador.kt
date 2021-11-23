package br.unipar.plano.domain.prestador.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdPrestador(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable

/* Classe criada simplesmente com a finalidade de facilitar o desenvolvimento
 * Visto que a implementação da mesma depende de outra equipe */