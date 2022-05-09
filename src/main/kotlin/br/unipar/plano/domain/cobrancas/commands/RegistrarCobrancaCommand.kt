package br.unipar.plano.domain.cobrancas.commands

import br.unipar.plano.domain.cobrancas.model.Cirurgia
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.model.Procedimento
import java.time.LocalDate

data class RegistrarCobrancaCommand(
    val idContrato: IdContrato,
    val dataEmissao: LocalDate,
    val cirurgias: Collection<Cirurgia>,
    val procedimentos: Collection<Procedimento>
)