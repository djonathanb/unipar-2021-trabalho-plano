package br.unipar.plano.domain.reembolso.model

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
class ReembolsoRejeitado (
    @Id
    val id: UUID,

    @Column(nullable = true)
    val dataRejeicao: LocalDate,

    @Column(nullable = false)
    val motivo: String,

    @Column(nullable = false)
    val agente: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    val reembolso: Reembolso
)