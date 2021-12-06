package br.unipar.plano.domain.planos.model.factories

import br.unipar.plano.domain.planos.model.IdPlano
import br.unipar.plano.domain.planos.model.TipoAbrangencia
import br.unipar.plano.domain.planos.model.TipoAcomodacao
import java.math.BigDecimal
import java.util.*

val PLANO_CCMA_50_ID = IdPlano(UUID.fromString("4a2fadf0-4f59-4a2e-b310-a2b264e9e88a"))
val PLANO_CCMA_50_ABRANGENCIA = TipoAbrangencia.NACIONAL
const val PLANO_CCMA_50_NOME = "CCMA + 50%"
val PLANO_CCMA_50_ACOMODACAO = TipoAcomodacao.INDIVIDUAL
const val PLANO_CCMA_50_OBSTETRICIA = false
const val PLANO_CCMA_50_TRANPORTEAEREO = false
val PLANO_CCMA_50_VALORBASE = BigDecimal.valueOf(100.0)

