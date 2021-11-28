package br.unipar.plano.domain.credenciamentos.model.clinicaHospital

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class IdPrestadorClinicaHospital(@field:JsonValue var id: UUID = UUID.randomUUID()) : Serializable