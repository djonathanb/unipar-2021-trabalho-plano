package br.unipar.plano.domain.contratos.planos.usecases

import br.unipar.plano.domain.contratos.planos.model.IdPlano


interface DeletaPlanoUseCase {

    fun executa(idPlano: IdPlano)

}
