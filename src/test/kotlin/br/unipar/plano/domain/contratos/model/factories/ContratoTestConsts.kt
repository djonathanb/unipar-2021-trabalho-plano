package br.unipar.plano.domain.contratos.model.factories

import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.pessoas.model.IdPessoa
import java.util.*

val CONTRATO_CO_ID = IdContrato(UUID.fromString("3a3fadf0-4f59-4a2e-c315-a2b254e9e88a"))
val CONTRATO_CO_STATUS = StatusContrato.ATIVO
val CONTRATO_CO_IDTITULAR = UUID.fromString("4a56cdf0-4f59-4a2e-c315-a2b254e9e88a")
val CONTRATO_CO_IDPLANO = UUID.fromString("5a56cdf0-4f59-4a2e-c315-a2b254e9e88a")

val CONTRATO_CO_IDPESSOA = UUID.fromString("5a56cfd0-4f59-4a2e-c315-a2b254e9e88a")
val CONTRATO_CO_NOMEPESSOA = "JEVER DA SILVA"
val PESSOA_CO_ID = IdPessoa(UUID.fromString("4a45bdf0-4f59-4a2e-c315-a2b254e9e88a"))

