package br.unipar.plano.domain.credenciamentos.services.clinicaHospital


import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.interfaces.rest.credenciamentos.clinicaHospital.PrestClinHospDTO
import br.unipar.plano.interfaces.rest.credenciamentos.clinicaHospital.PrestClinHospDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.clinicaHospital.PrestClinHospSummaryDTO


interface PrestClinHospAppService {
    fun cria(prestClinHospDTO: PrestClinHospDTO): IdPrestadorClinicaHospital
    fun lista(): List<PrestClinHospSummaryDTO>
    fun buscaPorId(idPrestadorClinicaHospital: IdPrestadorClinicaHospital): PrestClinHospDetailsDTO
}

