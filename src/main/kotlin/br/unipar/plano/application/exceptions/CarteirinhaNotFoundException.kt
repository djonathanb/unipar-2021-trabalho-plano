package br.unipar.plano.application.exceptions

import br.unipar.plano.application.exceptions.NotFoundException

class CarteirinhaNotFoundException(idCarteirinha: String) : NotFoundException("Carteirinha com o id ${idCarteirinha} não encontrada!") {
}