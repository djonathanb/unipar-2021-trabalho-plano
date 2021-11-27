package br.unipar.plano.domain.credenciamentos.services.impl

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.PrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.PrestadorClinicaHospitalRepository
import br.unipar.plano.domain.credenciamentos.services.PrestClinHospQueryService
import org.springframework.stereotype.Service

@Service
class PrestClinHospQueryImpl (private val prestadorClinicaHospitalRepository: PrestadorClinicaHospitalRepository): PrestClinHospQueryService {

    override fun lista(): List<PrestadorClinicaHospital> = prestadorClinicaHospitalRepository.findAll()

    override fun buscaPorId(idPrestadorClinicaHospital: IdPrestadorClinicaHospital): PrestadorClinicaHospital = prestadorClinicaHospitalRepository.findById(idPrestadorClinicaHospital).orElseThrow{
        Exception("Prestador médico com id ${idPrestadorClinicaHospital} não encontrado")
    }
}

