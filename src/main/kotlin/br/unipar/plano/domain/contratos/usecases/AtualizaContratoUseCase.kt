package br.unipar.plano.domain.contratos.usecases

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.planos.model.Plano

interface AtualizaContratoUseCase {

    fun executa( idContrato: IdContrato, transformation: (Contrato) -> Contrato,)

}