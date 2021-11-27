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
    val abrangencia: TipoAbrangencia,

        @Column
    val estadoPlanoAtual: String,

        @Column
    val estadoSolicitacaoReembolso: String,

        @OneToOne(cascade = [CascadeType.ALL])
    val usuario: Usuario
)
