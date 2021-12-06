package br.unipar.plano.application.exceptions

class CarteirinhaCadastradaException(idCarteirinha: String) : NotFoundException("Carteirinha ${idCarteirinha} já cadastrada!")