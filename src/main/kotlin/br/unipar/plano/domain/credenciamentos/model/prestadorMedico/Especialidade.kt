package br.unipar.plano.domain.credenciamentos.model.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Especialidade (

    @field:EmbeddedId
    val id: IdPrestadorMedico,

    @Column(nullable = false)
    val nomeEspecialidade: String

)