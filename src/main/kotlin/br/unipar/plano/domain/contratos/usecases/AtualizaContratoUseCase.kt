package br.unipar.plano.domain.contratos.usecases

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato

interface AtualizaContratoUseCase {

    fun executa( idContrato: IdContrato, transformation: (Contrato) -> Contrato )

}