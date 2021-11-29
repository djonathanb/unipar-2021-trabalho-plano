package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.impl

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospitalRepository
import br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.AtualizaPrestClinHospUseCase
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl.PrestMedicoNotFoundException
import org.springframework.stereotype.Service

@Service
class AtualizaPrestClinHospUseCaseImpl(private val prestadorClinicaHospitalRepository: PrestadorClinicaHospitalRepository): AtualizaPrestClinHospUseCase{
    override fun executa(idPrestadorClinicaHospital: IdPrestadorClinicaHospital, transformation: (PrestadorClinicaHospital) -> PrestadorClinicaHospital
    ) {
        val prestadorMedico = prestadorClinicaHospitalRepository.findById(idPrestadorClinicaHospital).orElseThrow { PrestClinHospNotFoundException(idPrestadorClinicaHospital) }
        prestadorClinicaHospitalRepository.save(transformation(prestadorMedico).with(id = idPrestadorClinicaHospital))
    }
}