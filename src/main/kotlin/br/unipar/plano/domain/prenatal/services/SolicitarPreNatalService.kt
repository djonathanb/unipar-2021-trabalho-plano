package br.unipar.plano.domain.prenatal.services

import br.unipar.plano.domain.prenatal.model.IdPreNatal
import br.unipar.plano.interfaces.rest.prenatal.PreNatalDTO
import br.unipar.plano.interfaces.rest.prenatal.PreNatalDetailsDTO
import br.unipar.plano.interfaces.rest.prenatal.PreNatalSummaryDTO

interface SolicitarPreNatalService {
    fun solicitar(preNatalDTO: PreNatalDTO): IdPreNatal
    fun cancelar(idPreNatal: IdPreNatal): PreNatalDetailsDTO
    fun autorizar(idPreNatal: IdPreNatal): PreNatalDetailsDTO
    fun listar(): List<PreNatalSummaryDTO>
    fun buscarPorId(idPreNatal: IdPreNatal): PreNatalDetailsDTO
}