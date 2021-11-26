package br.unipar.plano.domain.contratos.model.factories

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato
import java.time.LocalDate
import java.util.*

fun idContrato (static: Boolean = true) = if (static) {
    CONTRATO_CO_ID
} else {
    IdContrato()
}

fun contrato(
    idContrato: IdContrato = idContrato(),
    dataCadastro: LocalDate = LocalDate.of(2018,11,26),
    dataVencimento: LocalDate = LocalDate.of(2021,11,26),
    idPlano: UUID = CONTRATO_CO_IDPLANO,
    idTitular : UUID = CONTRATO_CO_IDTITULAR,
) = Contrato(
    idContrato = idContrato,
    dataContratacao = dataCadastro,
    dataContratoFinal = dataVencimento,
    idPlano = idPlano,
    idTitular = idTitular,
)

