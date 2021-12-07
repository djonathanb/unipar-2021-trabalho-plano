package br.unipar.plano.domain.carteirinha.model

import java.util.*
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class MotivoExtravio(
        @field:Id
        val numeroCarteirinha: String,

        @Column(nullable = false)
        val dataExtravio: Date,

        @Column(nullable = false)
        val motivoExtravio: String) {

    fun registrarExtravio(): MotivoExtravio {
        val m = MotivoExtravio(dataExtravio = Date(), numeroCarteirinha = numeroCarteirinha, motivoExtravio = motivoExtravio);

        return m
    }
}