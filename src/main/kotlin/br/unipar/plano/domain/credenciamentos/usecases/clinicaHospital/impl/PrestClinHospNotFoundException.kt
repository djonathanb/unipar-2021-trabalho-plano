package br.unipar.plano.domain.credenciamentos.usecases.clinicaHospital.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital


class PrestClinHospNotFoundException(idPrestadorClinicaHospital: IdPrestadorClinicaHospital) : NotFoundException("Prestador Clinica/Hospital com id ${idPrestadorClinicaHospital.id} não encontrada")
