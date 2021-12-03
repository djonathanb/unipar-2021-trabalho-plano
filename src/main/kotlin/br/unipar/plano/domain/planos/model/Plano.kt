package br.unipar.plano.domain.planos.model

import java.math.BigDecimal
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
        val transporteAereo: Boolean = false,

        @Column(nullable = false)
        val valorBase: BigDecimal = BigDecimal.valueOf(100.0),

        ) {

    fun with(
            id: IdPlano = this.id,
            nome: String = this.nome,
            abrangencia: TipoAbrangencia = this.abrangencia,
            acomodacao: TipoAcomodacao = this.acomodacao,
            obstetricia: Boolean = this.obstetricia,
            transporteAereo: Boolean = this.transporteAereo,
            valorBase: BigDecimal = this.valorBase
    ) = copy(
            id = id,
            nome = nome,
            abrangencia = abrangencia,
            acomodacao = acomodacao,
            obstetricia = obstetricia,
            transporteAereo = transporteAereo,
            valorBase = valorBase
    )

    private fun copy(
            id: IdPlano = this.id,
            nome: String = this.nome,
            abrangencia: TipoAbrangencia = this.abrangencia,
            acomodacao: TipoAcomodacao = this.acomodacao,
            obstetricia: Boolean = this.obstetricia,
            transporteAereo: Boolean = this.transporteAereo,
            valorBase: BigDecimal = this.valorBase
    ) = Plano(
            id = id,
            nome = nome,
            abrangencia = abrangencia,
            acomodacao = acomodacao,
            obstetricia = obstetricia,
            transporteAereo = transporteAereo,
            valorBase = valorBase
    )




}
