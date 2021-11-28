package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.impl

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospitalRepository
import br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.CriaPrestClinHospUseCase
import org.springframework.stereotype.Service


@Service
class CriaPrestClinHospUseCaseImpl(private val prestadorClinicaHospitalRepository: PrestadorClinicaHospitalRepository) :
    CriaPrestClinHospUseCase {

    override fun executa(prestadorClinicaHospital: PrestadorClinicaHospital): PrestadorClinicaHospital {
        return prestadorClinicaHospitalRepository.save(prestadorClinicaHospital)
    }

}


