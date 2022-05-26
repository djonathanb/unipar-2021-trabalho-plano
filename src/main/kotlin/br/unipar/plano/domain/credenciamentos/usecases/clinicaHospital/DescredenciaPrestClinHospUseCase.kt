package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital

interface DescredenciaPrestClinHospUseCase {
    fun executa(idPrestadorClinicaHospital: IdPrestadorClinicaHospital)
}