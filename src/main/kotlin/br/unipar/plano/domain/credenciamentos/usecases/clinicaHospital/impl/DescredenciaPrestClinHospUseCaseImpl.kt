package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.impl;

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospitalRepository;
import br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.DescredenciaPrestClinHospUseCase
import org.springframework.stereotype.Service;

@Service
class DescredenciaClinHospUseCaseImpl(private val prestadorClinicaHospitalRepository: PrestadorClinicaHospitalRepository) : DescredenciaPrestClinHospUseCase{

        override fun executa(idPrestadorClinicaHospital:IdPrestadorClinicaHospital){
                val prestClinica=prestadorClinicaHospitalRepository.findById(idPrestadorClinicaHospital).orElseThrow{PrestClinHospNotFoundException(idPrestadorClinicaHospital)}
                prestadorClinicaHospitalRepository.save(prestClinica.descredencia())
        }
}