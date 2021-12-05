package br.unipar.plano.domain.reembolso.model

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
class RejeicaoReembolso (
    @field:EmbeddedId
    val id: IdRejeicao,

    @Column(nullable = true)
    val dataRejeicao: LocalDate,

    @Column(nullable = false)
    val motivo: String,

    @Column(nullable = false)
    val agente: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    val reembolso: Reembolso
)