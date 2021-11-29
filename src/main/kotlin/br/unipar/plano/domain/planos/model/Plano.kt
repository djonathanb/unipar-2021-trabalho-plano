package br.unipar.plano.domain.planos.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Plano(

    @Id
    val id: UUID
) {
}