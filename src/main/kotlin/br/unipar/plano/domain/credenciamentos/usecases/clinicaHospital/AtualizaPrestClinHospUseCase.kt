package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospital


interface AtualizaPrestClinHospUseCase {

    fun executa(idPrestadorClinicaHospital: IdPrestadorClinicaHospital, transformation: (PrestadorClinicaHospital) -> PrestadorClinicaHospital)

}