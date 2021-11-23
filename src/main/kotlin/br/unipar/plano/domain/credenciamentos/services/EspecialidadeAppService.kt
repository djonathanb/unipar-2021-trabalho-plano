package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.credenciamentos.model.IdEspecialidade
import br.unipar.plano.interfaces.rest.credenciamentos.EspecialidadeDTO
import br.unipar.plano.interfaces.rest.credenciamentos.EspecialidadeDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.EspecialidadeSummaryDTO
import br.unipar.plano.interfaces.rest.credenciamentos.PrestadorMedicoDetailsDTO


interface EspecialidadeAppService {
    fun cria(especialidadeDTO: EspecialidadeDTO): IdEspecialidade
    fun lista(): List<EspecialidadeSummaryDTO>
    fun buscaPorId(idEspecialidade: IdEspecialidade): EspecialidadeDetailsDTO
}