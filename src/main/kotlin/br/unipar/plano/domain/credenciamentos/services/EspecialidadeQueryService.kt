package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.credenciamentos.model.Especialidade
import br.unipar.plano.domain.credenciamentos.model.IdEspecialidade

interface EspecialidadeQueryService {
    fun lista(): List<Especialidade>
    fun buscaPorId(idEspecialidade: IdEspecialidade): Especialidade
}