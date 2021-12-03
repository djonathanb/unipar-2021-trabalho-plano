package br.unipar.plano.domain.reembolso.model

import java.time.LocalDate
import java.util.*
import javax.persistence.*

enum class StatusCarteirinha {
    ATIVA, VENCIDA
}

@Entity
class Carteirinha (
    @Id
    val id: UUID,

    @Column(nullable = false)
    val numero: String,

    @Enumerated(EnumType.STRING)
    val statusCarteirinha: StatusCarteirinha = StatusCarteirinha.ATIVA,

    @Column(nullable = false)
    val dataValidade: LocalDate,

    @OneToOne(cascade = [CascadeType.ALL])
    val usuario: Usuario,
)