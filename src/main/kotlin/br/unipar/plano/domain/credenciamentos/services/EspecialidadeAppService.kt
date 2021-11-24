package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.credenciamentos.model.IdEspecialidade
import br.unipar.plano.interfaces.rest.credenciamentos.*

interface EspecialidadeAppService {
    fun cria(espDTO: EspDTO): IdEspecialidade
    fun lista(): List<EspecialidadeSummaryDTO>
    fun buscaPorId(idEspecialidade: IdEspecialidade): EspecialidadeDetailsDTO
}