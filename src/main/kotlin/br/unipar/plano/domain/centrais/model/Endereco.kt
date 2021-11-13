package br.unipar.plano.domain.centrais.model

class Endereco(
    val cidade: Int,
    val cep: String,
    val bairro: String,
    val logradouro: String,
    val numero: Int,
    val complemento: String
)
