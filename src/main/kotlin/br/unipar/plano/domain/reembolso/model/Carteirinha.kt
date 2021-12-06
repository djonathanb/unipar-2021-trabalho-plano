package br.unipar.plano.domain.reembolso.model

import java.time.LocalDate
import javax.persistence.*

enum class StatusCarteirinha {
    ATIVA, VENCIDA
}

@Entity
class Carteirinha (
    @field:EmbeddedId
    val id: IdCarteirinha,

    @Column(nullable = false)
    val numero: String,

    @Enumerated(EnumType.STRING)
    val statusCarteirinha: StatusCarteirinha = StatusCarteirinha.ATIVA,

    @Column(nullable = false)
    val dataValidade: LocalDate,

    @ManyToOne(cascade = [CascadeType.ALL])
    val usuario: Usuario,
)