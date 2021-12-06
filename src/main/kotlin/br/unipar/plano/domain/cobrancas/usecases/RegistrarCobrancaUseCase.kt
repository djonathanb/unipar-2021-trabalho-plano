package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.interfaces.rest.cobrancas.CobrancaDetailsDTO
import java.time.LocalDate

interface RegistrarCobrancaUseCase {

    fun executa(contrato: Contrato, dataEmissao: LocalDate): Cobranca

}
