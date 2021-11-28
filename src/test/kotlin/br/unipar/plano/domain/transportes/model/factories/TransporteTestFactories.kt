package br.unipar.plano.domain.centrais.model.factories

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.carteirinhas.model.IdCarteirinha
import br.unipar.plano.domain.centrais.model.*
import br.unipar.plano.domain.transportes.model.EnderecoTransporte
import java.time.LocalDate
import java.util.*
import javax.persistence.*


fun transporte(
    id: IdTransporte=TRANSPORTE_CO_ID,
    carteirinha: Carteirinha  = Carteirinha(CARTEIRINHA_TESTE_ID),
    dataSolicitacao: LocalDate = LocalDate.now(),
    status: StatusTransporte = StatusTransporte.PENDENTE,
    enderecoOrigem: EnderecoTransporte  = enderecoTransporteOrigem(),
    enderecoDestino: EnderecoTransporte = enderecoTransporteDestino(),
    tipoTransporte: TipoTransporte = TipoTransporte.AMBULANCIA
) = Transporte(
    id= id,
    carteirinha= carteirinha,
    dataSolicitacao= dataSolicitacao,
    status= status,
    enderecoOrigem = enderecoOrigem,
    enderecoDestino= enderecoDestino,
    tipoTransporte = tipoTransporte
)

fun enderecoTransporteOrigem(
    idTransporte: IdTransporte=TRANSPORTE_CO_ID,
    cidade: Int = TRANSPORTE_ORIGEM_ENDERECO_CIDADE,
    cep: String = TRANSPORTE_ORIGEM_ENDERECO_CEP,
    bairro: String = TRANSPORTE_ORIGEM_ENDERECO_BAIRRO,
    logradouro: String = TRANSPORTE_ORIGEM_ENDERECO_LOGRADOURO,
    numero: Int = TRANSPORTE_ORIGEM_ENDERECO_NUMERO,
    complemento: String = TRANSPORTE_ORIGEM_ENDERECO_COMPLEMENTO
) = EnderecoTransporte(
    idTransporte=idTransporte,
    cidade = cidade,
    cep = cep,
    bairro = bairro,
    logradouro = logradouro,
    numero = numero,
    complemento = complemento
)

fun enderecoTransporteDestino(
    idTransporte: IdTransporte=TRANSPORTE_CO_ID,
    cidade: Int = TRANSPORTE_DESTINO_ENDERECO_CIDADE,
    cep: String = TRANSPORTE_DESTINO_ENDERECO_CEP,
    bairro: String = TRANSPORTE_DESTINO_ENDERECO_BAIRRO,
    logradouro: String = TRANSPORTE_DESTINO_ENDERECO_LOGRADOURO,
    numero: Int = TRANSPORTE_DESTINO_ENDERECO_NUMERO,
    complemento: String = TRANSPORTE_DESTINO_ENDERECO_COMPLEMENTO
) = EnderecoTransporte(
    idTransporte=idTransporte,
    cidade = cidade,
    cep = cep,
    bairro = bairro,
    logradouro = logradouro,
    numero = numero,
    complemento = complemento
)




