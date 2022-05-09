package br.unipar.plano.domain.cobrancas.usecases

import br.unipar.plano.domain.cobrancas.commands.CancelarCobrancaCommand
import br.unipar.plano.domain.cobrancas.model.Cobranca

interface CancelarCobrancaUseCase {

    fun executa(command: CancelarCobrancaCommand): Cobranca

}
