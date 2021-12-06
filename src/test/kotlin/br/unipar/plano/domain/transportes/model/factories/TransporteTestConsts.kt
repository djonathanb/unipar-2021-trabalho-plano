package br.unipar.plano.domain.centrais.model.factories

import br.unipar.plano.application.factories.CIDADE_TOLEDO_ID
import br.unipar.plano.domain.carteirinhas.model.IdCarteirinha
import br.unipar.plano.domain.centrais.model.*
import br.unipar.plano.domain.transportes.model.StatusTransporte
import br.unipar.plano.domain.transportes.model.TipoTransporte
import java.util.*

val TRANSPORTE_ID = IdTransporte(UUID.fromString("017f7c2e-55ae-4ed7-847b-f7337a599e0f"))
val TRANSPORTE_CARTEIRINHA_ID = IdCarteirinha(UUID.fromString("a59f85f2-037b-4a2e-a101-17cfa96ffca4"))
val TRANSPORTE_STATUS = StatusTransporte.PENDENTE
val TRANSPORTE_TIPO_TRANSPORTE = TipoTransporte.UTI_MOVEL

const val TRANSPORTE_ENDERECO_ORIGEM_CIDADE = CIDADE_TOLEDO_ID
const val TRANSPORTE_ENDERECO_ORIGEM_CEP = "85909640"
const val TRANSPORTE_ENDERECO_ORIGEM_BAIRRO = "Vila Pioneiro"
const val TRANSPORTE_ENDERECO_ORIGEM_LOGRADOURO = "Rua Garibaldi"
const val TRANSPORTE_ENDERECO_ORIGEM_NUMERO = 456
const val TRANSPORTE_ENDERECO_ORIGEM_COMPLEMENTO = "Casa"

const val TRANSPORTE_ENDERECO_DESTINO_CIDADE = CIDADE_TOLEDO_ID
const val TRANSPORTE_ENDERECO_DESTINO_CEP = "85905080"
const val TRANSPORTE_ENDERECO_DESTINO_BAIRRO = "Jardim La Salle"
const val TRANSPORTE_ENDERECO_DESTINO_LOGRADOURO = "Rua Santo Ângelo"
const val TRANSPORTE_ENDERECO_DESTINO_NUMERO = 1145
const val TRANSPORTE_ENDERECO_DESTINO_COMPLEMENTO = "AP 13"
