package br.unipar.plano.domain.transportes.model.factories

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.carteirinhas.model.IdCarteirinha
import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.transportes.model.TipoTransporte
import br.unipar.plano.domain.transportes.model.Transporte
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.domain.transportes.model.EnderecoTransporte

fun idTransporte(static: Boolean = true) = if (static) {
    TRANSPORTE_ID
} else {
    IdTransporte()
}

fun idCarteirinha(static: Boolean = true) = if (static) {
    TRANSPORTE_CARTEIRINHA_ID
} else {
    IdCarteirinha()
}

fun transporte(
    idTransporte: IdTransporte = idTransporte(),
    enderecoOrigem: EnderecoTransporte = enderecoOrigem(idTransporte = idTransporte),
    enderecoDestino: EnderecoTransporte = enderecoDestino(idTransporte = idTransporte),
    tipoTransporte: TipoTransporte = TRANSPORTE_TIPO_TRANSPORTE,
) = Transporte(
    id = idTransporte,
    carteirinha = carteirinha(idCarteirinha()),
    enderecoOrigem = enderecoOrigem,
    enderecoDestino = enderecoDestino,
    tipoTransporte =  tipoTransporte
)

fun carteirinha(
    idCarteirinha: IdCarteirinha = idCarteirinha(),
) = Carteirinha(
    id = idCarteirinha,
)

fun enderecoOrigem(
    idTransporte: IdTransporte = idTransporte(),
    cidade: Int = TRANSPORTE_ENDERECO_ORIGEM_CIDADE,
    cep: String = TRANSPORTE_ENDERECO_ORIGEM_CEP,
    bairro: String = TRANSPORTE_ENDERECO_ORIGEM_BAIRRO,
    logradouro: String = TRANSPORTE_ENDERECO_ORIGEM_LOGRADOURO,
    numero: Int = TRANSPORTE_ENDERECO_ORIGEM_NUMERO,
    complemento: String = TRANSPORTE_ENDERECO_ORIGEM_COMPLEMENTO,
) = EnderecoTransporte(
    idTransporte = idTransporte,
    cidade = cidade,
    cep = cep,
    bairro = bairro,
    logradouro = logradouro,
    numero = numero,
    complemento = complemento
)

fun enderecoDestino(
    idTransporte: IdTransporte = idTransporte(),
    cidade: Int = TRANSPORTE_ENDERECO_DESTINO_CIDADE,
    cep: String = TRANSPORTE_ENDERECO_DESTINO_CEP,
    bairro: String = TRANSPORTE_ENDERECO_DESTINO_BAIRRO,
    logradouro: String = TRANSPORTE_ENDERECO_DESTINO_LOGRADOURO,
    numero: Int = TRANSPORTE_ENDERECO_DESTINO_NUMERO,
    complemento: String = TRANSPORTE_ENDERECO_DESTINO_COMPLEMENTO,
) = EnderecoTransporte(
    idTransporte = idTransporte,
    cidade = cidade,
    cep = cep,
    bairro = bairro,
    logradouro = logradouro,
    numero = numero,
    complemento = complemento
)
