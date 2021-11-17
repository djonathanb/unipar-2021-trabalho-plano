package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.PrestadorMedico
import br.unipar.plano.interfaces.rest.credenciamentos.PrestMedDTO

interface PrestMedQueryService {
    fun lista(): List<PrestMedDTO>
    fun buscaPorId(idPrestadorMedico: IdPrestadorMedico): PrestadorMedico
}