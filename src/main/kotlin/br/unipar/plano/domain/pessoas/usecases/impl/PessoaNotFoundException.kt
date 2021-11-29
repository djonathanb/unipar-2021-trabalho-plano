package br.unipar.plano.domain.pessoas.usecases.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.pessoas.model.IdPessoa

class PessoaNotFoundException(idPessoa: IdPessoa) : NotFoundException("Pessoa com id ${idPessoa.id} não encontrada")