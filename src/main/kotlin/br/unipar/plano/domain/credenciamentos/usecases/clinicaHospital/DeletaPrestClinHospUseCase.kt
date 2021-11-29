package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital

interface DeletaPrestClinHospUseCase {
    fun executa(idPrestadorClinicaHospital: IdPrestadorClinicaHospital)
}