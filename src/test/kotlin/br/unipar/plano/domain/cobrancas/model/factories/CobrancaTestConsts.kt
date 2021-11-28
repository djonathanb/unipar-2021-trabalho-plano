package br.unipar.plano.domain.cobrancas.model.factories

import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

val COBRANCA_ID = IdCobranca(UUID.fromString("4a2fadf0-4f59-4a2e-b310-a2b264e9e88a"))
val VALOR_CONTRATO = BigDecimal.valueOf(1100)
val VALOR_ADICIONAL_CONSULTA = BigDecimal.valueOf(10)
val VALOR_ADICIONAL_CIRURGIA = BigDecimal.valueOf(1000)
val VALOR_ADICIONAL_IDADE = BigDecimal.ONE
val DATA_EMISSAO_COBRANCA = LocalDate.of(2021, 10, 11)
val DATA_VENCIMENTO_COBRANCA = LocalDate.of(2021, 10, 21)
val STATUS_CoBRANCA_ABERTO = StatusCobranca.ABERTO
val PLANO_ID_1 = UUID.fromString("18ac3b92-b3c1-4145-84d4-21fa8e48c4ee")
val PLANO_ID_2 = UUID.fromString("0f043bac-e7c8-43ca-86e7-1b8baf32c281")
val PROCEDIMENTO_ID_1 = UUID.fromString("96e596cf-9838-4094-b75b-c7c18cb3af7a")
val PROCEDIMENTO_ID_2 = UUID.fromString("1af6e0e4-12e8-484d-80b3-5bf39e0a69a6")
val CIRURGIA_ID_1 = UUID.fromString("012f4c4e-f381-4cce-9ce0-5c1ecd3d94cb")
val CIRURGIA_ID_2 = UUID.fromString("8ff864db-ff0e-4587-8095-aaf74adc2366")
val USUARIO_ID_1 = UUID.fromString("b091d158-8296-414a-9cae-6efe9c0bd3f5")
val USUARIO_ID_2 = UUID.fromString("61529bd7-c6d2-4025-8e78-a0cc7bce3206")
val VALOR_PLANO_500REAIS = BigDecimal.valueOf(500L)
val VALOR_PLANO_600REAIS = BigDecimal.valueOf(600L)