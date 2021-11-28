package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospital

interface CriaPrestClinHospUseCase {
    fun executa(prestadorClinicaHospital: PrestadorClinicaHospital): PrestadorClinicaHospital
}

