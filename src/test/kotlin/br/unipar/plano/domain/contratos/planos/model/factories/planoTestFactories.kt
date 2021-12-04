package br.unipar.plano.domain.contratos.planos.model.factories

import br.unipar.plano.domain.contratos.planos.model.IdPlano
import br.unipar.plano.domain.contratos.planos.model.Plano
import br.unipar.plano.domain.contratos.planos.model.TipoAbrangencia
import br.unipar.plano.domain.contratos.planos.model.TipoAcomodacao


fun idPlano(static: Boolean = true) = if (static) {
    PLANO_CO_ID
} else {
    IdPlano()
}

fun plano(
    idPlano: IdPlano = idPlano(),
    nome: String = PLANO_CO_NOME,
    abrangencia: TipoAbrangencia = PLANO_CO_ABRANGENCIA,
    acomodacao: TipoAcomodacao = PLANO_CO_ACOMODACAO,
    obstetricia: Boolean = PLANO_CO_OBSTETRICIA,
    transporteaereo: Boolean = PLANO_CO_TRANPORTEAEREO,
    valorbase: Double = PLANO_CO_VALORBASE
) = Plano(
    id = idPlano,
    nome = nome,
    abrangencia = abrangencia,
    acomodacao = acomodacao,
    obstetricia = obstetricia,
    transporteaereo = transporteaereo,
    valorbase = valorbase
)