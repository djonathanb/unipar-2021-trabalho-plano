package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.cobrancas.commands.RegistrarCobrancaCommand
import br.unipar.plano.domain.cobrancas.model.Cobranca

interface RegistrarCobrancaUseCase {

    fun executa(
        command: RegistrarCobrancaCommand
    ): Cobranca
}
