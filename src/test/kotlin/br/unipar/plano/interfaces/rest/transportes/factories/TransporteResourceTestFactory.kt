package br.unipar.plano.interfaces.rest.transportes.factories

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.transportes.model.TipoTransporte
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.domain.transportes.model.factories.idCarteirinha
import br.unipar.plano.interfaces.rest.carteirinhas.CarteirinhaSummaryDTO
import br.unipar.plano.interfaces.rest.transportes.EnderecoTransporteDTO
import br.unipar.plano.interfaces.rest.transportes.TransporteDTO

fun transporteDTO(
    carteirinha: CarteirinhaSummaryDTO = CarteirinhaSummaryDTO.toDTO(Carteirinha(idCarteirinha(true))),
    enderecoOrigem: EnderecoTransporteDTO = enderecoOrigemDTO(),
    enderecoDestino: EnderecoTransporteDTO = enderecoDestinoDTO(),
    tipoTransporte: TipoTransporte = TRANSPORTE_TIPO_TRANSPORTE
) = TransporteDTO(
    carteirinha = carteirinha,
    enderecoOrigem = enderecoOrigem,
    enderecoDestino = enderecoDestino,
    tipoTransporte = tipoTransporte
)

fun enderecoOrigemDTO(
    cidade: Int = TRANSPORTE_ENDERECO_ORIGEM_CIDADE,
    cep: String = TRANSPORTE_ENDERECO_ORIGEM_CEP,
    bairro: String = TRANSPORTE_ENDERECO_ORIGEM_BAIRRO,
    logradouro: String = TRANSPORTE_ENDERECO_ORIGEM_LOGRADOURO,
    numero: Int = TRANSPORTE_ENDERECO_ORIGEM_NUMERO,
    complemento: String = TRANSPORTE_ENDERECO_ORIGEM_COMPLEMENTO,
) = EnderecoTransporteDTO(
    cidade = cidade,
    cep = cep,
    bairro = bairro,
    logradouro = logradouro,
    numero = numero,
    complemento = complemento
)

fun enderecoDestinoDTO(
    cidade: Int = TRANSPORTE_ENDERECO_DESTINO_CIDADE,
    cep: String = TRANSPORTE_ENDERECO_DESTINO_CEP,
    bairro: String = TRANSPORTE_ENDERECO_DESTINO_BAIRRO,
    logradouro: String = TRANSPORTE_ENDERECO_DESTINO_LOGRADOURO,
    numero: Int = TRANSPORTE_ENDERECO_DESTINO_NUMERO,
    complemento: String = TRANSPORTE_ENDERECO_DESTINO_COMPLEMENTO,
) = EnderecoTransporteDTO(
    cidade = cidade,
    cep = cep,
    bairro = bairro,
    logradouro = logradouro,
    numero = numero,
    complemento = complemento
)
