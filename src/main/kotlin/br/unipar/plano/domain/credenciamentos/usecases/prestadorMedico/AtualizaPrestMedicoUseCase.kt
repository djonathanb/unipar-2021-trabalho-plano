package br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico

interface AtualizaPrestMedicoUseCase {

    fun executa(idPrestadorMedico: IdPrestadorMedico, transformation: (PrestadorMedico) -> PrestadorMedico)

}
