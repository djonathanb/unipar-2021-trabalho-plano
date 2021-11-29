package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.impl

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospitalRepository
import br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.DeletaPrestClinHospUseCase
import org.springframework.stereotype.Service

@Service
class DeletaPrestClinHospUseCaseImpl(private val prestadorClinicaHospitalRepository: PrestadorClinicaHospitalRepository):
    DeletaPrestClinHospUseCase {

    override fun executa(idPrestadorClinicaHospital: IdPrestadorClinicaHospital) {
        val prestadorMedico = prestadorClinicaHospitalRepository.findById(idPrestadorClinicaHospital)
            .orElseThrow { PrestClinHospNotFoundException(idPrestadorClinicaHospital) }
        return prestadorClinicaHospitalRepository.delete(prestadorMedico)
    }
}