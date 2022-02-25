package br.unipar.plano.domain.centrais.usecases

import br.unipar.plano.domain.cobrancas.model.Cirurgia
import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.model.Procedimento
import java.time.LocalDate

interface RegistrarCobrancaUseCase {

    fun executa(
        idContrato: IdContrato,
        dataEmissao: LocalDate,
        cirurgias: Collection<Cirurgia>,
        procedimentos: Collection<Procedimento>
    ): Cobranca
}
