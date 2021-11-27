package br.unipar.plano.domain.centrais.model.factories

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.Endereco
import br.unipar.plano.domain.centrais.model.IdCentral

fun idCentral(static: Boolean = true) = if (static) {
    CENTRAL_CO_ID
} else {
    IdCentral()
}

fun central(
    idCentral: IdCentral = idCentral(),
    nome: String = CENTRAL_CO_NOME,
    cnpj: String = CENTRAL_CO_CNPJ,
    endereco: Endereco = endereco(idCentral = idCentral)
) = Central(
    id = idCentral,
    nome = nome,
    cnpj = cnpj,
    endereco = endereco
)

fun endereco(
    idCentral: IdCentral = idCentral(),
    cidade: Int = CENTRAL_CO_ENDERECO_CIDADE,
    cep: String = CENTRAL_CO_ENDERECO_CEP,
    bairro: String = CENTRAL_CO_ENDERECO_BAIRRO,
    logradouro: String = CENTRAL_CO_ENDERECO_LOGRADOURO,
    numero: Int = CENTRAL_CO_ENDERECO_NUMERO,
    complemento: String = CENTRAL_CO_ENDERECO_COMPLEMENTO
) = Endereco(
    idCentral = idCentral,
    cidade = cidade,
    cep = cep,
    bairro = bairro,
    logradouro = logradouro,
    numero = numero,
    complemento = complemento
)
