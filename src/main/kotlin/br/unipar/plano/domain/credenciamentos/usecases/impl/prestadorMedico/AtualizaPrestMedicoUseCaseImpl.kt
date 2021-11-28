package br.unipar.plano.domain.credenciamentos.usecases.impl.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.AtualizaPrestMedicoUseCase
import org.springframework.stereotype.Service

@Service
class AtualizaPrestMedicoUseCaseImpl(private val prestadorMedicoRepository: PrestadorMedicoRepository) :
    AtualizaPrestMedicoUseCase {

    override fun executa(idPrestadorMedico: IdPrestadorMedico, transformation: (PrestadorMedico) -> PrestadorMedico) {
        val central = prestadorMedicoRepository.findById(idPrestadorMedico).orElseThrow { PrestMedicoNotFoundException(idPrestadorMedico) }
        prestadorMedicoRepository.save(transformation(central).with(id = idPrestadorMedico))
    }

}