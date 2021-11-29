package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.impl;


import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospitalRepository;
import org.springframework.stereotype.Service;

@Service
class AtualizaPrestClinHospUseCaseImpl(private val prestadorClinicaHospitalRepository: PrestadorClinicaHospitalRepository){


}

/*
        override fun executa(idPrestadorMedico: IdPrestadorMedico, transformation: (PrestadorMedico) -> PrestadorMedico) {
        val central = prestadorMedicoRepository.findById(idPrestadorMedico).orElseThrow { PrestMedicoNotFoundException(idPrestadorMedico) }
        prestadorMedicoRepository.save(transformation(central).with(id = idPrestadorMedico))
        }

 */

