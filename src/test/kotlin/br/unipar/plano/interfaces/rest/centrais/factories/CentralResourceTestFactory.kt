package br.unipar.plano.interfaces.rest.centrais.factories

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.interfaces.rest.centrais.CentralDTO
import br.unipar.plano.interfaces.rest.centrais.EnderecoDTO
import java.util.*

fun idCentral(static: Boolean = true) = if (static) {
    IdCentral(UUID.fromString("4a2fadf0-4f59-4a2e-b310-a2b264e9e88a"))
} else {
    IdCentral()
}

fun centralDTO(
    nome: String = "Costa Oeste PR",
    cnpj: String = "69.036.127/0001-67",
    endereco: EnderecoDTO = enderecoDTO()
) = CentralDTO(
    nome = nome,
    cnpj = cnpj,
    endereco = endereco
)

fun enderecoDTO(
    cidade: Int = 41277700,
    cep: String = "85900000",
    bairro: String = "Centro",
    logradouro: String = "Rua Santos Dumont",
    numero: Int = 500,
    complemento: String = ""
) = EnderecoDTO(
    cidade = cidade,
    cep = cep,
    bairro = bairro,
    logradouro = logradouro,
    numero = numero,
    complemento = complemento
)
