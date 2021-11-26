package br.unipar.plano.domain.carteirinha.model

import java.util.*
import javax.persistence.Column
import javax.persistence.EmbeddedId

class MotivoExtravio(
        @field:EmbeddedId
        val numeroCarteirinha: IdCarteirinha,

        @Column(nullable = false)
        val dataExtravio: Date,

        @Column(nullable = false)
        val motivoExtravio: String) {
}