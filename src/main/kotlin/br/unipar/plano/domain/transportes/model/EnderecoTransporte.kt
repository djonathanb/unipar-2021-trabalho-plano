package br.unipar.plano.domain.transportes.model

import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.enderecos.model.Endereco
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class EnderecoTransporte(

    @field:EmbeddedId
    val idTransporte: IdTransporte,

    cidade: Int,
    cep: String,
    bairro: String,
    logradouro: String,
    numero: Int,
    complemento: String,

    ) : Endereco(
    cidade,
    cep,
    bairro,
    logradouro,
    numero,
    complemento
)