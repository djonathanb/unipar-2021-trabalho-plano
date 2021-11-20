package br.unipar.plano.domain.credenciamentos.usecases

import br.unipar.plano.domain.credenciamentos.model.PrestadorClinicaHospital

interface CriaPrestClinHospUseCase {
    fun executa(prestadorClinicaHospital: PrestadorClinicaHospital): PrestadorClinicaHospital
}

