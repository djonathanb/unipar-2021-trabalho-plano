package br.unipar.plano.domain.contratos.usecases

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato

interface AtualizaCadastroUseCase {
    fun executa(idContrato: IdContrato, transformation: (Contrato) -> Contrato)
}