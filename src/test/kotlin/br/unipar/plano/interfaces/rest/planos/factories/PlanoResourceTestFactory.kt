package br.unipar.plano.interfaces.rest.planos.factories

import br.unipar.plano.domain.planos.model.IdPlano
import br.unipar.plano.domain.planos.model.TipoAbrangencia
import br.unipar.plano.domain.planos.model.TipoAcomodacao
import br.unipar.plano.domain.planos.model.factories.*
import br.unipar.plano.interfaces.rest.planos.PlanoDTO
import br.unipar.plano.interfaces.rest.planos.PlanoDetailsDTO
import br.unipar.plano.interfaces.rest.planos.PlanoSummaryDTO
import java.math.BigDecimal

fun planoDTO(
        nome: String = PLANO_CCMA_50_NOME,
        abrangencia: TipoAbrangencia = PLANO_CCMA_50_ABRANGENCIA,
        acomodacao: TipoAcomodacao = PLANO_CCMA_50_ACOMODACAO,
        obstetricia: Boolean = PLANO_CCMA_50_OBSTETRICIA,
        transporteAereo: Boolean = PLANO_CCMA_50_TRANPORTEAEREO,
        valorBase: BigDecimal = PLANO_CCMA_50_VALORBASE
) = PlanoDTO(
        nome = nome,
        abrangencia = abrangencia,
        acomodacao = acomodacao,
        obstetricia = obstetricia,
        transporteAereo = transporteAereo,
        valorBase = valorBase
)

fun planoSummaryDTO(
    staticId: Boolean = true,
    idPlano: IdPlano = idPlano(staticId),
    nome: String = PLANO_CCMA_50_NOME,
    abrangencia: TipoAbrangencia = PLANO_CCMA_50_ABRANGENCIA,
    acomodacao: TipoAcomodacao = PLANO_CCMA_50_ACOMODACAO,
    obstetricia: Boolean = PLANO_CCMA_50_OBSTETRICIA,
    transporteAereo: Boolean = PLANO_CCMA_50_TRANPORTEAEREO,
    valorBase: BigDecimal = PLANO_CCMA_50_VALORBASE,

) = PlanoSummaryDTO(
    id = idPlano,
    nome = nome,
    abrangencia = abrangencia,
    acomodacao = acomodacao,
    obstetricia = obstetricia,
    transporteAereo = transporteAereo,
    valorBase = valorBase
)

fun planoDetailsDTO(
    staticId: Boolean = true,
    idPlano: IdPlano = idPlano(staticId),
    planoData: PlanoDTO = planoDTO()
) = PlanoDetailsDTO(
    id = idPlano,
    planoData = planoData
)
