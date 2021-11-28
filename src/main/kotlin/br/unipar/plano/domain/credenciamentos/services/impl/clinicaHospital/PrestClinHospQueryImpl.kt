package br.unipar.plano.domain.credenciamentos.services.impl.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.PrestadorClinicaHospitalRepository
import br.unipar.plano.domain.credenciamentos.services.clinicaHospital.PrestClinHospQueryService
import org.springframework.stereotype.Service

@Service
class PrestClinHospQueryImpl (private val prestadorClinicaHospitalRepository: PrestadorClinicaHospitalRepository):
    PrestClinHospQueryService {

    override fun lista(): List<PrestadorClinicaHospital> = prestadorClinicaHospitalRepository.findAll()

    override fun buscaPorId(idPrestadorClinicaHospital: IdPrestadorClinicaHospital): PrestadorClinicaHospital = prestadorClinicaHospitalRepository.findById(idPrestadorClinicaHospital).orElseThrow{
        Exception("Prestador médico com id ${idPrestadorClinicaHospital} não encontrado")
    }
}

