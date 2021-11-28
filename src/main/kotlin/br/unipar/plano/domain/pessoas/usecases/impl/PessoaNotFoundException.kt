package br.unipar.plano.domain.pessoas.usecases.impl

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.pessoas.model.IdDependente

class PessoaNotFoundException(idPessoa: IdDependente) : NotFoundException("Pessoa com id ${idPessoa.id} não encontrada")