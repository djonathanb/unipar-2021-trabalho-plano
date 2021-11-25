package br.unipar.plano.domain.contratos.usecases


import br.unipar.plano.domain.centrais.model.Contrato

interface CriaContratoUseCase {
    fun cria(contrato: Contrato): Contrato
}