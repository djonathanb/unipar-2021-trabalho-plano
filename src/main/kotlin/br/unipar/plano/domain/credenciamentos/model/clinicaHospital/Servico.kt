package br.unipar.plano.domain.credenciamentos.model.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
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