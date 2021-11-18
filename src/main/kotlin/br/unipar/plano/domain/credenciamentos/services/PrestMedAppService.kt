package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.interfaces.rest.credenciamentos.PrestMedDTO
import br.unipar.plano.interfaces.rest.credenciamentos.PrestadorMedicoDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.PrestadorMedicoSummaryDTO


interface PrestMedAppService {
    fun cria(prestMedDTO: PrestMedDTO): IdPrestadorMedico
    fun lista(): List<PrestadorMedicoSummaryDTO>
    fun buscaPorId(idPrestadorMedico: IdPrestadorMedico): PrestadorMedicoDetailsDTO
}