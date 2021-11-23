package br.unipar.plano.domain.carteirinha.model

import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Carteirinha(

        @field:EmbeddedId
        val id: IdCarteirinha

);