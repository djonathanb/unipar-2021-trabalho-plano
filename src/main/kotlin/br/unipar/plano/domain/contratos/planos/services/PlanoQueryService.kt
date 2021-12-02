package br.unipar.plano.domain.planos.services

import br.unipar.plano.domain.planos.model.Plano
import br.unipar.plano.domain.planos.model.IdPlano

interface PlanoQueryService {
    fun lista(): List<Plano>
    fun buscaPorId(idPlano: IdPlano): Plano
}
