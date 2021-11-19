package br.unipar.plano.domain.credenciamentos.model

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Especialidade (

    @field:EmbeddedId
    val idPrestadorMedico: IdPrestadorMedico,

    @Column(nullable = false)
    val nomeEspecialidade: String

)