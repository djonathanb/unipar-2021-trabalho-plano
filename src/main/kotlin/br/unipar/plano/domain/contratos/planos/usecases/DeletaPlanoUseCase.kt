package br.unipar.plano.domain.planos.usecases

import br.unipar.plano.domain.planos.model.IdPlano

interface DeletaPlanoUseCase {

    fun executa(idPlano: IdPlano)

}
