package br.unipar.plano.domain.credenciamentos.usecases.impl

import br.unipar.plano.domain.credenciamentos.model.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.usecases.CriaPrestMedicoUseCase
import org.springframework.stereotype.Service

@Service
class CriaPrestMedicoUseCaseImpl(private val prestadorMedicoRepository: PrestadorMedicoRepository): CriaPrestMedicoUseCase {

    override fun executa(prestadorMedico: PrestadorMedico): PrestadorMedico {
        return prestadorMedicoRepository.save(prestadorMedico)
    }
}