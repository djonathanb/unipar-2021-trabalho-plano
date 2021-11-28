package br.unipar.plano.domain.credenciamentos.usecases.impl.prestadorMedico

import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.PrestadorMedicoRepository
import br.unipar.plano.domain.credenciamentos.usecases.prestadorMedico.CriaPrestMedicoUseCase
import org.springframework.stereotype.Service

@Service
class CriaPrestMedicoUseCaseImpl(private val prestadorMedicoRepository: PrestadorMedicoRepository):
    CriaPrestMedicoUseCase {

    override fun executa(prestadorMedico: PrestadorMedico): PrestadorMedico {
        return prestadorMedicoRepository.save(prestadorMedico)
    }
}