package br.unipar.plano.domain.prenatais.services

import br.unipar.plano.domain.prenatais.model.IdPreNatal
import br.unipar.plano.interfaces.rest.prenatais.PreNatalDTO
import br.unipar.plano.interfaces.rest.prenatais.PreNatalDetailsDTO
import br.unipar.plano.interfaces.rest.prenatais.PreNatalSummaryDTO

interface SolicitarPreNatalService {
    fun solicitar(preNatalDTO: PreNatalDTO): IdPreNatal
    fun cancelar(idPreNatal: IdPreNatal): PreNatalDetailsDTO
    fun autorizar(idPreNatal: IdPreNatal): PreNatalDetailsDTO
    fun listar(): List<PreNatalSummaryDTO>
    fun buscarPorId(idPreNatal: IdPreNatal): PreNatalDetailsDTO
}