package br.unipar.plano.domain.pessoas.model.factories

import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.model.StatusEstadoCivil
import java.time.LocalDate
import java.util.*


val PESSOA_CO_ID = IdPessoa(UUID.fromString("4a45bdf0-4f59-4a2e-c315-a2b254e9e88a"))
val PESSOA_CO_NOMEPESSOA = "JEVER DA SILVA"
val PESSOA_CO_ENDERECO = "RUA SILVEIRA"
val PESSOA_CO_RG = "1526262662"
val PESSOA_CO_CPF = "10811097935"
val PESSOA_CO_DT_NASCIMENTO = LocalDate.of(1998,11,26)
val PESSOA_CO_NOME_MAE = "IRENI DE SOUZA"
val PESSOA_CO_NOME_PAI = "IRINEU DE SOUZA"
val PESSOA_CO_ESTADO_CIVIL = StatusEstadoCivil.AMASIADO