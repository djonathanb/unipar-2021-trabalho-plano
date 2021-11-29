package br.unipar.plano.domain.centrais.model.factories

import br.unipar.plano.application.factories.CIDADE_TOLEDO_ID
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.model.StatusCentral
import java.util.*

val CENTRAL_CO_ID = IdCentral(UUID.fromString("4a2fadf0-4f59-4a2e-b310-a2b264e9e88a"))
val CENTRAL_CO_STATUS = StatusCentral.CRIADA
const val CENTRAL_CO_NOME = "Costa Oeste PR"
const val CENTRAL_CO_CNPJ = "69.036.127/0001-67"
const val CENTRAL_CO_ENDERECO_CIDADE = CIDADE_TOLEDO_ID
const val CENTRAL_CO_ENDERECO_CEP = "85900000"
const val CENTRAL_CO_ENDERECO_BAIRRO = "Centro"
const val CENTRAL_CO_ENDERECO_LOGRADOURO = "Rua Santos Dumont"
const val CENTRAL_CO_ENDERECO_NUMERO = 500
const val CENTRAL_CO_ENDERECO_COMPLEMENTO = "Sala 2"

