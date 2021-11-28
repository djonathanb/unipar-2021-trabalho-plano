package br.unipar.plano.domain.centrais.model.factories

import br.unipar.plano.application.factories.CIDADE_CASCAVEL_ID
import br.unipar.plano.application.factories.CIDADE_TOLEDO_ID
import br.unipar.plano.domain.carteirinhas.model.IdCarteirinha
import br.unipar.plano.domain.centrais.model.IdTransporte
import java.util.*

val TRANSPORTE_CO_ID = IdTransporte(UUID.fromString("dd0b9eb5-6305-48cd-bb1d-4a4655534dad"))
val CARTEIRINHA_TESTE_ID =  IdCarteirinha(UUID.fromString("58dfcf37-cbed-4a66-b191-379e30dd4757"))
const val TRANSPORTE_ORIGEM_ENDERECO_CIDADE = CIDADE_TOLEDO_ID
const val TRANSPORTE_ORIGEM_ENDERECO_CEP = "85900000"
const val TRANSPORTE_ORIGEM_ENDERECO_BAIRRO = "Centro"
const val TRANSPORTE_ORIGEM_ENDERECO_LOGRADOURO = "Rua Santos Dumont"
const val TRANSPORTE_ORIGEM_ENDERECO_NUMERO = 500
const val TRANSPORTE_ORIGEM_ENDERECO_COMPLEMENTO = "Sala 2"

const val TRANSPORTE_DESTINO_ENDERECO_CIDADE = CIDADE_CASCAVEL_ID
const val TRANSPORTE_DESTINO_ENDERECO_CEP = "85806300"
const val TRANSPORTE_DESTINO_ENDERECO_BAIRRO = "Centro"
const val TRANSPORTE_DESTINO_ENDERECO_LOGRADOURO = "Rua Itaquatiaras"
const val TRANSPORTE_DESTINO_ENDERECO_NUMERO = 769
const val TRANSPORTE_DESTINO_ENDERECO_COMPLEMENTO = "Hospital Uopeccan de Cascavel"
