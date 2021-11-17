package br.unipar.plano.domain.credenciamentos.usecases

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.credenciamentos.model.PrestadorClinicaHospital

interface CriaPrestClinHospUseCase {
    fun executa(prestadorClinicaHospital: PrestadorClinicaHospital): Central
}