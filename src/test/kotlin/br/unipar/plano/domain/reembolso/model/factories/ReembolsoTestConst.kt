package br.unipar.plano.domain.reembolso.model.factories

import br.unipar.plano.domain.reembolso.model.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

val REEMBOLSO_ID = IdReembolso(UUID.fromString("4a2fadf0-4f59-4a2e-b310-a2b264e9e88a"))
val REEMBOLSO_ESTADO = Estado.PARANA
val REEMBOLSO_STATUS = StatusReembolso.EM_ANALIZE
val REEMBOLSO_VALOR = BigDecimal("250")
val REEMBOLSO_DATA_SOLICITACAO = LocalDate.now()
val REEMBOLSO_DATA_ANALIZE = LocalDate.now()

val REJEICAO_ID = IdRejeicao(UUID.fromString("4a2fadf0-4f59-4a2e-b310-a2b224e9e88a"))
val REJEICAO_DATA = LocalDate.now()
const val REJEICAO_MOTIVO = "reembolso na mesma area de abrangencia do plano"
const val REJEICAO_AGENTE = 1

val USUARIO_ID = IdUsuario(UUID.fromString("1234adf0-4f59-4a2e-b310-a2b264e9e88a"))
const val USUARIO_NOME = "85900000"
const val USUARIO_CPF = "85900000"

val CARTEIRINHA_ID = IdCarteirinha(UUID.fromString("1234adff-4f59-4a2e-b310-a2b264e9e88a"))
const val CARTEIRINHA_NUMERO = "PR9999999-99"
val CARTEIRNHA_STATUS = StatusCarteirinha.ATIVA
val CARTEIRNHA_DATA_VALIDADE = LocalDate.now()

val PLANO_ID = UUID.fromString("1234adff-4f59-4a2e-b310-a2b264e9e88a")
val PLANO_TIPO_ABRANGENCIA = TipoAbrangencia.ESTADUAL
val PLANO_ATEA_ABRANGENCIA = Estado.PARANA
