package br.unipar.plano.domain.dependentes.services


import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.dependentes.model.IdDependente


interface DependenteQueryService {

    fun lista(): List<Dependente>
    fun buscaPorId(idDependente: IdDependente): Dependente
    fun buscaPorContrato(contrato: Contrato): List<Dependente>
    
}