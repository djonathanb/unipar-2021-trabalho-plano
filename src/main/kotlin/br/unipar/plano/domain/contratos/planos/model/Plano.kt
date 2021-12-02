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

    fun with(
            id: IdPlano = this.id,
            nome: String = this.nome,
            abrangencia: TipoAbrangencia = this.abrangencia,
            acomodacao: TipoAcomodacao = this.acomodacao,
            obstetricia: Boolean = this.obstetricia,
            transporteaereo: Boolean = this.transporteaereo,
            valorbase: Double = this.valorbase
    ) = copy(
            id = id,
            nome = nome,
            abrangencia = abrangencia,
            acomodacao = acomodacao,
            obstetricia = obstetricia,
            transporteaereo = transporteaereo,
            valorbase = valorbase
    )

    private fun copy(
            id: IdPlano = this.id,
            nome: String = this.nome,
            abrangencia: TipoAbrangencia = this.abrangencia,
            acomodacao: TipoAcomodacao = this.acomodacao,
            obstetricia: Boolean = this.obstetricia,
            transporteaereo: Boolean = this.transporteaereo,
            valorbase: Double = this.valorbase
    ) = Plano(
            id = id,
            nome = nome,
            abrangencia = abrangencia,
            acomodacao = acomodacao,
            obstetricia = obstetricia,
            transporteaereo = transporteaereo,
            valorbase = valorbase
    )




}
