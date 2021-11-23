package br.unipar.plano.domain.enderecos.model

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class Endereco(

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