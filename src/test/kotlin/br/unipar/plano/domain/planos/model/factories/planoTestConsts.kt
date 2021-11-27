package br.unipar.plano.domain.planos.model.factories

import br.unipar.plano.domain.planos.model.IdPlano
import br.unipar.plano.domain.planos.model.TipoAbrangencia
import br.unipar.plano.domain.planos.model.TipoAcomodacao
import java.util.*

val PLANO_CO_ID = IdPlano(UUID.fromString("4a2fadf0-4f59-4a2e-b310-a2b264e9e88a"))
val PLANO_CO_ABRANGENCIA = TipoAbrangencia.NACIONAL
const val PLANO_CO_NOME = "CCMA + 50%"
val PLANO_CO_ACOMODACAO = TipoAcomodacao.INDIVIDUAL
const val PLANO_CO_OBSTETRICIA = false
const val PLANO_CO_TRANPORTEAEREO = false
const val PLANO_CO_VALORBASE = 100.00

