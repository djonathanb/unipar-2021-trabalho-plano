package br.unipar.plano.domain.contratos.services

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.planos.model.Plano
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.pessoas.model.Pessoa
import java.util.*


interface ContratoQueryService {

    fun lista(): List<Contrato>
    fun buscaPorId(idContrato: IdContrato): Contrato
    fun buscaPorPlano(idPlano: Plano): List<Contrato>
    fun findByTitularAndStatus (titular: Pessoa, statusContrato: StatusContrato): List<Contrato>
    fun buscaPorPlanoeStatus(idPlano: Plano, status: Optional<List<StatusContrato>>): List<Contrato>
}