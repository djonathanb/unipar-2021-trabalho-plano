package br.unipar.plano.domain.cobrancas.model.factories

import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.model.Plano
import br.unipar.plano.domain.cobrancas.model.Usuario

fun idContrato(static: Boolean = true) = if (static) {
    CONTRATO_ID
} else {
    IdContrato()
}

fun contrato(
    dependentes: Collection<Usuario> = mutableListOf(
        Usuario(
            id = DEPENDENTE_ID_1,
            plano = Plano(
                id = PLANO_ID_1,
                valorBase = VALOR_BASE_DEPENDENTE_500
            ),
            DATA_NASCIMENTO_DEPENDENTE_1
        ), Usuario(
            id = DEPENDENTE_ID_2,
            plano = Plano(
                id = PLANO_ID_2,
                valorBase = VALOR_BASE_DEPENDENTE_600
            ),
            DATA_NASCIMENTO_DEPENDENTE_2
        )
    )
): Contrato {
    val contrato = Contrato(
        id = CONTRATO_ID,
        dependentes = dependentes
    )
    return contrato
}
