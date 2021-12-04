package br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico

interface DeletaPrestMedicoUseCase {

    fun executa(idPrestadorMedico: IdPrestadorMedico)

}
