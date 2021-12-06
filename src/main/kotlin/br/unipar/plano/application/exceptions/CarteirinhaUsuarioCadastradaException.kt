package br.unipar.plano.application.exceptions

class CarteirinhaUsuarioCadastradaException(idCarteirinha: String, idUsuario: Int) : NotFoundException("Usuário ${idUsuario} já possui uma carteirinha válida: ${idCarteirinha}")