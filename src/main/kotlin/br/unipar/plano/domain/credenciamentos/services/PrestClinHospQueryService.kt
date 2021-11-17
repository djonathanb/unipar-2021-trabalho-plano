package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.PrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.PrestadorMedico

interface PrestClinHospQueryService {
    fun lista(): List<PrestadorClinicaHospital>
    fun buscaPorId(idPrestadorClinicaHospital: IdPrestadorClinicaHospital): PrestadorClinicaHospital
}