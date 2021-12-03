package br.unipar.plano.interfaces.rest.centrais.factories

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.model.StatusCentral
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.interfaces.rest.centrais.dto.CentralDTO
import br.unipar.plano.interfaces.rest.centrais.dto.CentralDetailsDTO
import br.unipar.plano.interfaces.rest.centrais.dto.CentralSummaryDTO
import br.unipar.plano.interfaces.rest.centrais.dto.EnderecoDTO

fun centralDTO(
    nome: String = CENTRAL_CO_NOME,
    cnpj: String = CENTRAL_CO_CNPJ,
    endereco: EnderecoDTO = enderecoDTO()
) = CentralDTO(
    nome = nome,
    cnpj = cnpj,
    endereco = endereco
)

fun enderecoDTO(
    cidade: Int = CENTRAL_CO_ENDERECO_CIDADE,
    cep: String = CENTRAL_CO_ENDERECO_CEP,
    bairro: String = CENTRAL_CO_ENDERECO_BAIRRO,
    logradouro: String = CENTRAL_CO_ENDERECO_LOGRADOURO,
    numero: Int = CENTRAL_CO_ENDERECO_NUMERO,
    complemento: String = CENTRAL_CO_ENDERECO_COMPLEMENTO
) = EnderecoDTO(
    cidade = cidade,
    cep = cep,
    bairro = bairro,
    logradouro = logradouro,
    numero = numero,
    complemento = complemento
)

fun centralSummaryDTO(
    staticId: Boolean = true,
    idCentral: IdCentral = idCentral(staticId),
    nome: String = CENTRAL_CO_NOME,
    cidade: Int = CENTRAL_CO_ENDERECO_CIDADE
) = CentralSummaryDTO(
    id = idCentral,
    nome = nome,
    cidade = cidade
)

fun centralDetailsDTO(
    staticId: Boolean = true,
    idCentral: IdCentral = idCentral(staticId),
    status: StatusCentral = CENTRAL_CO_STATUS,
    centralData: CentralDTO = centralDTO()
) = CentralDetailsDTO(
    id = idCentral,
    status = status,
    centralData = centralData
)
