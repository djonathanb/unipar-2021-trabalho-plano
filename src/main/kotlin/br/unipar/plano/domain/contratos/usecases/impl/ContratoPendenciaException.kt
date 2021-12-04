package br.unipar.plano.domain.contratos.usecases.impl

import br.unipar.plano.domain.pessoas.model.Pessoa

class ContratoPendenciaException(titular: Pessoa) : RuntimeException("O titular com CPF ${titular.cpf} possui alguma pendencia aberta ou o prazo de carencia ainda não foi atingido")