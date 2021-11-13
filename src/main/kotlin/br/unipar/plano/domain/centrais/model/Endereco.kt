package br.unipar.plano.domain.centrais.model

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Endereco(

    @field:EmbeddedId
    val idCentral: IdCentral,

    @Column(nullable = false)
    val cidade: Int,

    @Column(nullable = false)
    val cep: String,

    @Column(nullable = false)
    val bairro: String,

    @Column(nullable = false)
    val logradouro: String,

    @Column(nullable = false)
    val numero: Int,

    @Column(nullable = false)
    val complemento: String

)
