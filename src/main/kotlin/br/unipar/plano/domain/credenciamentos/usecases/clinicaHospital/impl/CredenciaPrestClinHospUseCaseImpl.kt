package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.impl

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospitalRepository
import br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.CredenciaPrestClinHospUseCase
import org.springframework.stereotype.Service

@Service
class CredenciaPrestClinHospUseCaseImpl(private val prestadorClinicaHospitalRepository: PrestadorClinicaHospitalRepository): CredenciaPrestClinHospUseCase {

    override fun executa(idPrestadorClinicaHospital: IdPrestadorClinicaHospital) {
        val prestadorClinicaHospital = prestadorClinicaHospitalRepository.findById(idPrestadorClinicaHospital).orElseThrow{ PrestClinHospNotFoundException(idPrestadorClinicaHospital)}
        prestadorClinicaHospitalRepository.save(prestadorClinicaHospital.credencia())
    }
}