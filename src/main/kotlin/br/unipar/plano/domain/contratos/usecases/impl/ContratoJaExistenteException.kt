package br.unipar.plano.domain.contratos.usecases.impl

import br.unipar.plano.domain.pessoas.model.Pessoa

class ContratoJaExistenteException(titular: Pessoa) : RuntimeException("O titular com CPF ${titular.cpf} já possui contratos ativos !")