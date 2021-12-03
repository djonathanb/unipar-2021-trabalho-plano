package br.unipar.plano.domain.planos.model.factories

import br.unipar.plano.domain.planos.model.Plano
import br.unipar.plano.domain.planos.model.IdPlano
import br.unipar.plano.domain.planos.model.TipoAbrangencia
import br.unipar.plano.domain.planos.model.TipoAcomodacao
import java.math.BigDecimal

fun idPlano(static: Boolean = true) = if (static) {
    PLANO_CCMA_50_ID
} else {
    IdPlano()
}

fun plano(
        idPlano: IdPlano = idPlano(),
        nome: String = PLANO_CCMA_50_NOME,
        abrangencia: TipoAbrangencia = PLANO_CCMA_50_ABRANGENCIA,
        acomodacao: TipoAcomodacao = PLANO_CCMA_50_ACOMODACAO,
        obstetricia: Boolean = PLANO_CCMA_50_OBSTETRICIA,
        transporteAereo: Boolean = PLANO_CCMA_50_TRANPORTEAEREO,
        valorBase: BigDecimal = PLANO_CCMA_50_VALORBASE
) = Plano(
    id = idPlano,
    nome = nome,
    abrangencia = abrangencia,
    acomodacao = acomodacao,
    obstetricia = obstetricia,
    transporteAereo = transporteAereo,
    valorBase = valorBase
)