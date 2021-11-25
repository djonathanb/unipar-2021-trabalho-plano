package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.credenciamentos.model.IdServico
import br.unipar.plano.domain.credenciamentos.model.Servico

interface ServicoQueryService {
    fun lista(): List<Servico>
    fun buscaPorId(idServico: IdServico): Servico
}