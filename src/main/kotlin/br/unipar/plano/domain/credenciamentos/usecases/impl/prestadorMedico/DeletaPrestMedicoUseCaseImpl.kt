package br.unipar.plano.domain.credenciamentos.usecases.impl.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.DeletaPrestMedicoUseCase
import org.springframework.stereotype.Service

@Service
class DeletaPrestMedicoUseCaseImpl(private val prestadorMedicoRepository: PrestadorMedicoRepository): DeletaPrestMedicoUseCase {

    override fun executa(idPrestadorMedico: IdPrestadorMedico){
        val prestadorMedico = prestadorMedicoRepository.findById(idPrestadorMedico).orElseThrow{ PrestMedicoNotFoundException(idPrestadorMedico)}
        return prestadorMedicoRepository.delete(prestadorMedico)
    }

}