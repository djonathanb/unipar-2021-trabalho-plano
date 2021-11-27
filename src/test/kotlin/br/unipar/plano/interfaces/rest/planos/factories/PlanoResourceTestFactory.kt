package br.unipar.plano.interfaces.rest.planos.factories

import br.unipar.plano.domain.planos.model.IdPlano
import br.unipar.plano.domain.planos.model.TipoAbrangencia
import br.unipar.plano.domain.planos.model.TipoAcomodacao
import br.unipar.plano.domain.planos.model.factories.*
import br.unipar.plano.interfaces.rest.planos.PlanoDTO
import br.unipar.plano.interfaces.rest.planos.PlanoDetailsDTO
import br.unipar.plano.interfaces.rest.planos.PlanoSummaryDTO

fun planoDTO(
        nome: String = PLANO_CO_NOME,
        abrangencia: TipoAbrangencia = PLANO_CO_ABRANGENCIA,
        acomodacao: TipoAcomodacao = PLANO_CO_ACOMODACAO,
        obstetricia: Boolean = PLANO_CO_OBSTETRICIA,
        transporteaereo: Boolean = PLANO_CO_TRANPORTEAEREO,
        valorbase: Double = PLANO_CO_VALORBASE
) = PlanoDTO(
        nome = nome,
        abrangencia = abrangencia,
        acomodacao = acomodacao,
        obstetricia = obstetricia,
        transporteaereo = transporteaereo,
        valorbase = valorbase
)

fun planoSummaryDTO(
    staticId: Boolean = true,
    idPlano: IdPlano = idPlano(staticId),
    nome: String = PLANO_CO_NOME,
    abrangencia: TipoAbrangencia = PLANO_CO_ABRANGENCIA,
    acomodacao: TipoAcomodacao = PLANO_CO_ACOMODACAO,
    obstetricia: Boolean = PLANO_CO_OBSTETRICIA,
    transporteaereo: Boolean = PLANO_CO_TRANPORTEAEREO,
    valorbase: Double = PLANO_CO_VALORBASE,

) = PlanoSummaryDTO(
    id = idPlano,
    nome = nome,
    abrangencia = abrangencia,
    acomodacao = acomodacao,
    obstetricia = obstetricia,
    transporteaereo = transporteaereo,
    valorbase = valorbase
)

fun planoDetailsDTO(
    staticId: Boolean = true,
    idPlano: IdPlano = idPlano(staticId),
    planoData: PlanoDTO = planoDTO()
) = PlanoDetailsDTO(
    id = idPlano,
    planoData = planoData
)
