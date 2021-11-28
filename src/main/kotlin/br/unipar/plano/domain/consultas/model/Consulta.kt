package br.unipar.plano.domain.consultas.model

import org.springframework.beans.factory.annotation.Value
import javax.persistence.*

enum class TipoConsulta {
    GINECOLOGICA, PEDIATRICA;
}

@Entity
class Consulta(
    @field:EmbeddedId
    val id: IdConsulta,

    @Column
    val tipoConsulta: TipoConsulta,

    ) {
    fun with(
        //id: IdConsulta = this.id,
        tipoConsulta: TipoConsulta = this.tipoConsulta,
    ) = copy(
        //id = id,
        tipoConsulta = tipoConsulta,
    )

    private fun copy(
        //id: IdConsulta = this.id,
        tipoConsulta: TipoConsulta = this.tipoConsulta,
    ) = Consulta(
        //id = id,
        tipoConsulta = tipoConsulta,
    )
}