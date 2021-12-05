package br.unipar.plano.domain.reembolso.model

import java.util.*
import javax.persistence.*

enum class TipoAbrangencia {
    NACIONAL, ESTADUAL
}

@Entity
class Plano (
    @Id
    val id: UUID,

    @Enumerated(EnumType.STRING)
    val tipoAbrangencia: TipoAbrangencia,

    @Column(nullable = false)
    val areaAbrangencia: Estado,

    @OneToOne(cascade = [CascadeType.ALL])
    val usuario: Usuario
)
