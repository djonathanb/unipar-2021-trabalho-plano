package br.unipar.plano.domain.planos.model

import javax.persistence.*

enum class TipoAbrangencia {
    NACIONAL, ESTADUAL
}

enum class TipoAcomodacao {
    COMPARTILHADO, INDIVIDUAL
}

@Entity
class Plano(

        @field:EmbeddedId
        val id: IdPlano,

        @Column(nullable = false)
        val nome: String,

        @Enumerated(EnumType.STRING)
        val abrangencia: TipoAbrangencia = TipoAbrangencia.NACIONAL,

        @Enumerated(EnumType.STRING)
        val acomodacao: TipoAcomodacao = TipoAcomodacao.INDIVIDUAL,

        @Column(nullable = false)
        val obstetricia: Boolean = false,

        @Column(nullable = false)
        val transporteaereo: Boolean = false,

        @Column(nullable = false)
        val valorbase: Double = 100.0,

        ) {




}
