package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.interfaces.rest.credenciamentos.PrestClinHospDTO


interface PrestClinHospAppService {
    //Prestador Clinica Hospital Application Service : Spoiler
    fun cria(prestClinHospDTO: PrestClinHospDTO): IdPrestadorClinicaHospital
    fun lista(): List<PrestClinHospDTO>
    fun buscaPorId(idPrestadorClinicaHospital: IdPrestadorClinicaHospital): PrestClinHospDTO


}