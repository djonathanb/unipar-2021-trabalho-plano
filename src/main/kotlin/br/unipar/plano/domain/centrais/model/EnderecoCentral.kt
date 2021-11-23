package br.unipar.plano.domain.centrais.model

import br.unipar.plano.domain.enderecos.model.Endereco
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class EnderecoCentral(

    @field:EmbeddedId
    val idCentral: IdCentral,

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

