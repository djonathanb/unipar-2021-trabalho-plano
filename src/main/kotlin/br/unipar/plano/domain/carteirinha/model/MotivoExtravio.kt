package br.unipar.plano.domain.carteirinha.model

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class MotivoExtravio(
        @field:Id
        val numeroCarteirinha: String,

        @Column(nullable = false)
        val dataExtravio: LocalDate,

        @Column(nullable = false)
        val motivoExtravio: String) {

}