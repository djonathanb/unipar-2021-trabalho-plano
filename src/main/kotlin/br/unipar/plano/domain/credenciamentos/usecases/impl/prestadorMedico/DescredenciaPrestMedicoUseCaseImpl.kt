package br.unipar.plano.domain.credenciamentos.usecases.impl.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.CredenciaPrestMedicoUseCase
import org.springframework.stereotype.Service

@Service
class DescredenciaPrestMedicoUseCaseImpl(private val prestadorMedicoRepository: PrestadorMedicoRepository) :    CredenciaPrestMedicoUseCase {

    override fun executa(idPrestadorMedico: IdPrestadorMedico) {
        val prestMedico = prestadorMedicoRepository.findById(idPrestadorMedico).orElseThrow { PrestMedicoNotFoundException(idPrestadorMedico) }
        prestadorMedicoRepository.save(prestMedico.descredencia())
    }

}