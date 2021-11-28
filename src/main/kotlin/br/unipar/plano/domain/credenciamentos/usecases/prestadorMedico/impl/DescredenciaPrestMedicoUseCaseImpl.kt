package br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.impl

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.DescredenciaPrestMedicoUseCase
import org.springframework.stereotype.Service

@Service
class DescredenciaPrestMedicoUseCaseImpl(private val prestadorMedicoRepository: PrestadorMedicoRepository) : DescredenciaPrestMedicoUseCase {

    override fun executa(idPrestadorMedico: IdPrestadorMedico) {
        val prestMedico = prestadorMedicoRepository.findById(idPrestadorMedico).orElseThrow { PrestMedicoNotFoundException(idPrestadorMedico) }
        prestadorMedicoRepository.save(prestMedico.descredencia())
    }

}