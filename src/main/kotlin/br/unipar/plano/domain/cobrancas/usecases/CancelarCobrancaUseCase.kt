package br.unipar.plano.domain.cobrancas.usecases

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato

interface CancelarCobrancaUseCase {

    fun executa(idContrato: IdContrato, idCobranca: IdCobranca): Cobranca

}
