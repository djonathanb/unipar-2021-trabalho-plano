package br.unipar.plano.domain.contratos.model.factories

import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.model.StatusContrato
import java.time.LocalDate
import java.util.*

val CONTRATO_CO_ID = IdContrato(UUID.fromString("3a3fadf0-4f59-4a2e-c315-a2b254e9e88a"))
val CONTRATO_CO_STATUS = StatusContrato.ATIVO
val CONTRATO_CO_DATACADASTRO = LocalDate.of(2018,11,26)
val CONTRATO_CO_VENCIMENTO = LocalDate.of(2022,11,26)
val CONTRATO_CO_DATACANCELAMENTO = LocalDate.of(2021,11,26)


