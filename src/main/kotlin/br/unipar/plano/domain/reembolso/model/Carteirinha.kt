package br.unipar.plano.domain.reembolso.model

import java.util.*
import javax.persistence.*

enum class StatusCarteirinha {
    ATIVO, INATIVO
}

@Entity
class Carteirinha (
    @Id
    val id: UUID,

    @Column(nullable = false)
    val numero: String,

    @Enumerated(EnumType.STRING)
    val status: StatusCarteirinha = StatusCarteirinha.ATIVO,

    @OneToOne(cascade = [CascadeType.ALL])
    val usuario: Usuario,
)