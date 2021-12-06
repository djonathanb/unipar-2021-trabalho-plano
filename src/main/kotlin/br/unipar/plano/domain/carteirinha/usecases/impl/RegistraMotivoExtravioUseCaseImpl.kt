package br.unipar.plano.domain.carteirinha.usecases.impl

import br.unipar.plano.domain.carteirinha.model.MotivoExtravio
import br.unipar.plano.domain.carteirinha.services.MotivoExtravioQueryService
import br.unipar.plano.domain.carteirinha.usecases.RegistraMotivoExtravioUseCase
import org.springframework.stereotype.Service

@Service
class RegistraMotivoExtravioUseCaseImpl(private val motivoExtravioQueryService: MotivoExtravioQueryService) : RegistraMotivoExtravioUseCase {

    override fun RegistraMotivoExtravio(motivoExtravio: MotivoExtravio): MotivoExtravio {

        var motivoExtravioResult = motivoExtravio.registrarExtravio()

        return motivoExtravioQueryService.save(motivoExtravioResult)

    }
}