package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.interfaces.rest.credenciamentos.PrestMedDTO


interface PrestMedAppService {

    fun cria(prestMedDTO: PrestMedDTO): IdPrestadorMedico
    fun lista(): List<PrestMedDTO>
    fun buscaPorId(idPrestadorMedico: IdPrestadorMedico): PrestMedDTO
}