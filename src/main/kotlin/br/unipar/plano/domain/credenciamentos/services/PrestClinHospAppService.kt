package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorClinicaHospital
import br.unipar.plano.interfaces.rest.credenciamentos.PrestClinHospDTO


interface PrestClinHospAppService {
    fun cria(prestClinHospDTO: PrestClinHospDTO): IdPrestadorClinicaHospital
    fun lista(): List<PrestClinHospDTO>
    fun buscaPorId(idPrestadorClinicaHospital: IdPrestadorClinicaHospital): PrestClinHospDTO
}