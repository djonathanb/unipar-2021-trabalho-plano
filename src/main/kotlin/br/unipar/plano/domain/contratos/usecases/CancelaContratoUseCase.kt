package br.unipar.plano.domain.contratos.usecases



import br.unipar.plano.domain.contratos.model.IdContrato

interface CancelaContratoUseCase {

    fun executa(idContrato: IdContrato, existePendencia: Boolean)

}