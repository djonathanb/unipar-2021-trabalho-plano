package br.unipar.plano.domain.usuario

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

/*Classe que deve ser disponibilizada pela equipe responsável pelo usuário, criada para funcionamento da aplicação*/

@Embeddable
data class IdUsuario(@field:JsonValue var id: UUID = UUID.randomUUID()): Serializable