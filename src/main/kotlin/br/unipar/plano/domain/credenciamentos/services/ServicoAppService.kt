package br.unipar.plano.domain.credenciamentos.services

import br.unipar.plano.domain.credenciamentos.model.IdServico
import br.unipar.plano.interfaces.rest.credenciamentos.ServicoDTO
import br.unipar.plano.interfaces.rest.credenciamentos.ServicoDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.ServicoSummaryDTO

interface ServicoAppService {
    fun cria(servicoDTO: ServicoDTO): IdServico
    fun lista(): List<ServicoSummaryDTO>
    fun buscaPorId(idServico: IdServico): ServicoDetailsDTO
}