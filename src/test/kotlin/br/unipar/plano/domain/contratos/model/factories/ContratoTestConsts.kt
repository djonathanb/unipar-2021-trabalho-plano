package br.unipar.plano.domain.contratos.model.factories

import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.contratos.planos.model.IdPlano
import br.unipar.plano.domain.pessoas.model.IdPessoa
import java.util.*

val CONTRATO_CO_ID = IdContrato(UUID.fromString("3a3fadf0-4f59-4a2e-c315-a2b254e9e88a"))
val CONTRATO_CO_STATUS = StatusContrato.ATIVO
val PLANO_CO_ID = IdPlano(UUID.fromString("5a66cdf0-4f59-4a2e-c315-a2b254e9e88a"))

