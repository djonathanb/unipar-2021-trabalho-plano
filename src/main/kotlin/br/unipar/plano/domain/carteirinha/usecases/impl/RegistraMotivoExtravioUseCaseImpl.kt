package br.unipar.plano.domain.carteirinha.usecases.impl

import br.unipar.plano.domain.carteirinha.model.MotivoExtravio
import br.unipar.plano.domain.carteirinha.services.MotivoExtravioQueryService
import br.unipar.plano.domain.carteirinha.usecases.RegistraMotivoExtravioUseCase
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RegistraMotivoExtravioUseCaseImpl(private val motivoExtravioQueryService: MotivoExtravioQueryService) : RegistraMotivoExtravioUseCase {

    override fun RegistraMotivoExtravio(motivoExtravio: MotivoExtravio): MotivoExtravio {

        var motivoExtravioResult = MotivoExtravio(dataExtravio = LocalDate.now(), numeroCarteirinha = motivoExtravio.numeroCarteirinha, motivoExtravio = motivoExtravio.motivoExtravio)

        return motivoExtravioQueryService.save(motivoExtravioResult)

    }
}