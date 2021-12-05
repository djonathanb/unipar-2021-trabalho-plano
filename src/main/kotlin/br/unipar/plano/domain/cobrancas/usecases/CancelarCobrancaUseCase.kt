package br.unipar.plano.domain.cobrancas.usecases

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.interfaces.rest.cobrancas.CobrancaDetailsDTO

interface CancelarCobrancaUseCase {

    fun executa(idCobranca: IdCobranca) : Cobranca

}
