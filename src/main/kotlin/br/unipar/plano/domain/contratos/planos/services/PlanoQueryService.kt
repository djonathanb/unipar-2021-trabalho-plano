package br.unipar.plano.domain.contratos.planos.services

import br.unipar.plano.domain.contratos.planos.model.IdPlano
import br.unipar.plano.domain.contratos.planos.model.Plano


interface PlanoQueryService {
    fun lista(): List<Plano>
    fun buscaPorId(idPlano: IdPlano): Plano
}
