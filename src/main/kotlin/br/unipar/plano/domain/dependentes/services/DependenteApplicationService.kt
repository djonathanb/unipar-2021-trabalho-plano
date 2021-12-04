package br.unipar.plano.domain.dependentes.services


import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.dependentes.model.IdDependente
import br.unipar.plano.interfaces.rest.dependentes.DependenteDTO
import br.unipar.plano.interfaces.rest.dependentes.DependenteDetailsDTO

interface DependenteApplicationService {

    fun cria(dependenteDTO: DependenteDTO): IdDependente
    fun buscaPorId(idDependente: IdDependente): DependenteDetailsDTO
    fun deleta(idDependente: IdDependente)
    fun buscaPorContrato(idContrato: IdContrato): List<DependenteDetailsDTO>


}