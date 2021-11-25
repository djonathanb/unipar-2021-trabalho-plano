package br.unipar.plano.domain.contratos.usecases



import br.unipar.plano.domain.contratos.model.Contrato

interface CriaContratoUseCase {
    fun cria(contrato: Contrato): Contrato
}