package br.unipar.plano.domain.credenciamentos.services.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico


interface PrestMedQueryService {
    fun lista(): List<PrestadorMedico>
    fun buscaPorId(idPrestadorMedico: IdPrestadorMedico): PrestadorMedico
}

