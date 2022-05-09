package br.unipar.plano.domain.cobrancas.commands

import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato

data class CancelarCobrancaCommand(
    val idContrato: IdContrato,
    val idCobranca: IdCobranca
)