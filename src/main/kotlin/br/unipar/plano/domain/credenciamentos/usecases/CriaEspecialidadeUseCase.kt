package br.unipar.plano.domain.credenciamentos.usecases

import br.unipar.plano.domain.credenciamentos.model.Especialidade
import br.unipar.plano.domain.credenciamentos.model.PrestadorClinicaHospital

interface CriaEspecialidadeUseCase {
    fun executa(especialidade: Especialidade): Especialidade
}