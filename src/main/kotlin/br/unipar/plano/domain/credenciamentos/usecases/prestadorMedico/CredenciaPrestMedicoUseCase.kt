package br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico

interface CredenciaPrestMedicoUseCase {

    fun executa(idPrestadorMedico: IdPrestadorMedico)

}
