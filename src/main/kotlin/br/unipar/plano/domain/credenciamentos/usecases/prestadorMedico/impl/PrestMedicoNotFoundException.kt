package br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico

class PrestMedicoNotFoundException(idPrestadorMedico: IdPrestadorMedico) : NotFoundException("Prestador Medico com id ${idPrestadorMedico.id} não encontrada")
