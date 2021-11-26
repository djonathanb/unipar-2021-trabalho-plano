package br.unipar.plano.domain.carteirinha.model

import java.util.*
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Id

class MotivoExtravio(
        @field:Id
        val numeroCarteirinha: String,

        @Column(nullable = false)
        val dataExtravio: Date,

        @Column(nullable = false)
        val motivoExtravio: String) {
}