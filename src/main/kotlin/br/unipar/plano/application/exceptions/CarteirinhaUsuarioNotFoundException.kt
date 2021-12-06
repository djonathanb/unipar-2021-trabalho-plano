package br.unipar.plano.application.exceptions

import br.unipar.plano.application.exceptions.NotFoundException

class CarteirinhaUsuarioNotFoundException(idUsuario: Int) : NotFoundException("Nenhuma carteirinha Válida encontrada para o Usuário $idUsuario") {
}