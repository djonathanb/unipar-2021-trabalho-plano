package br.unipar.plano.domain.cobrancas.model.factories

import br.unipar.plano.domain.cobrancas.model.IdContrato
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

val VALOR_BASE_DEPENDENTE_500: BigDecimal = BigDecimal.valueOf(500)
val VALOR_BASE_DEPENDENTE_600: BigDecimal = BigDecimal.valueOf(600)
val DATA_NASCIMENTO_DEPENDENTE_1: LocalDate = LocalDate.of(1997, 11, 6)
val DATA_NASCIMENTO_DEPENDENTE_2: LocalDate = LocalDate.of(2000, 1, 1)
val CONTRATO_UUID = UUID.fromString("89d96d4c-5496-11ec-bf63-0242ac130002")
val CONTRATO_ID = IdContrato(CONTRATO_UUID)
val DEPENDENTE_ID_1 = UUID.fromString("8ed0dd32-e8de-420b-9566-e3df4969aa38")
val DEPENDENTE_ID_2 = UUID.fromString("4315c9d9-d3b4-46f4-b046-39f84ec90a4c")