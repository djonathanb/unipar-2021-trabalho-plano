package br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.AtualizaPrestMedicoUseCase
import org.springframework.stereotype.Service

@Service
class AtualizaPrestMedicoUseCaseImpl(private val prestadorMedicoRepository: PrestadorMedicoRepository) :
    AtualizaPrestMedicoUseCase {

    override fun executa(idPrestadorMedico: IdPrestadorMedico, transformation: (PrestadorMedico) -> PrestadorMedico) {
        val prestadorMedico = prestadorMedicoRepository.findById(idPrestadorMedico).orElseThrow { PrestMedicoNotFoundException(idPrestadorMedico) }
        prestadorMedicoRepository.save(transformation(prestadorMedico).with(id = idPrestadorMedico))
    }

}