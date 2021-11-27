package br.unipar.plano.domain.credenciamentos.model

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Servico  (
    @field:EmbeddedId
    val id: IdPrestadorClinicaHospital,

    @Column(nullable = false)
    val servico: String
)