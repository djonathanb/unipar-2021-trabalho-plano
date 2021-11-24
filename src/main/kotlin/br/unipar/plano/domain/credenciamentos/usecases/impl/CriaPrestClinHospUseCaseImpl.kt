package br.unipar.plano.domain.credenciamentos.usecases.impl

import br.unipar.plano.domain.credenciamentos.model.PrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.PrestadorClinicaHospitalRepository
import br.unipar.plano.domain.credenciamentos.usecases.CriaPrestClinHospUseCase
import org.springframework.stereotype.Service


@Service
class CriaPrestClinHospUseCaseImpl(private val prestadorClinicaHospitalRepository: PrestadorClinicaHospitalRepository) : CriaPrestClinHospUseCase {

    override fun executa(prestadorClinicaHospital: PrestadorClinicaHospital): PrestadorClinicaHospital {
        return prestadorClinicaHospitalRepository.save(prestadorClinicaHospital)
    }

}


