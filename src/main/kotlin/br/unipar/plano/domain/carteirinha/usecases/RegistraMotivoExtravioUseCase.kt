package br.unipar.plano.domain.carteirinha.usecases

import br.unipar.plano.domain.carteirinha.model.MotivoExtravio
import org.springframework.stereotype.Service

@Service
interface RegistraMotivoExtravioUseCase {
    fun RegistraMotivoExtravio(motivoExtravio: MotivoExtravio): MotivoExtravio
}