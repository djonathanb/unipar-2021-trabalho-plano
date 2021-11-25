package br.unipar.plano.domain.centrais.model

import java.util.*
import javax.persistence.*

enum class StatusContrato {
    ATIVO, CANCELADO
}

@Entity
class Contrato {

    @field:EmbeddedId
    val idContrato: IdContrato,

    @Column(nullable = false)
    val dataContratacao: Date,

    @Column(nullable = false)
    val dataContratofinal: Date,

    @Column(nullable = false)
    val idPlano: Int,

    @Column(nullable = false)
    val idTitular: Int,

    @Enumerated(EnumType.STRING)
    val status: StatusContrato = StatusContrato.ATIVO
}