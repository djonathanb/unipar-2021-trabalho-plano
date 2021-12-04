package br.unipar.plano.domain.cobrancas.model.factories

import br.unipar.plano.domain.cobrancas.model.*
import java.util.*

fun contrato(): Contrato {
    val contrato = Contrato(
        id = UUID.randomUUID(),
        procedimentos = mutableListOf(Procedimento(id = UUID.randomUUID()), Procedimento(id = UUID.randomUUID())),
        cirurgias = mutableListOf(Cirurgia(id = UUID.randomUUID()), Cirurgia(id = UUID.randomUUID())),
        dependentes = mutableListOf(
            Usuario(
                id = UUID.randomUUID(),
                plano = Plano(
                    id = UUID.randomUUID(),
                    valorBase = VALOR_BASE_DEPENDENTE_500
                ),
                DATA_NASCIMENTO_DEPENDENTE_1
            ), Usuario(
                id = UUID.randomUUID(),
                plano = Plano(
                    id = UUID.randomUUID(),
                    valorBase = VALOR_BASE_DEPENDENTE_600
                ),
                DATA_NASCIMENTO_DEPENDENTE_2
            )
        )
    )
    return contrato
}
