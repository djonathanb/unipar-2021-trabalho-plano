package br.unipar.plano.domain.contratos.usecases.impl

import br.unipar.plano.domain.pessoas.model.Pessoa

class ContratoDowngradeException(titular: Pessoa) : RuntimeException("Não é possivel fazer o downgrade do plano contratado de ${titular.nome}")