package br.unipar.plano.domain.credenciamentos.services.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospital

interface PrestClinHospQueryService {
    fun lista(): List<PrestadorClinicaHospital>
    fun buscaPorId(idPrestadorClinicaHospital: IdPrestadorClinicaHospital): PrestadorClinicaHospital
}

