package br.unipar.plano.domain.carteirinha.services.impl

import br.unipar.plano.domain.carteirinha.model.MotivoExtravio
import br.unipar.plano.domain.carteirinha.model.MotivoExtravioRepository
import br.unipar.plano.domain.carteirinha.services.MotivoExtravioQueryService
import org.springframework.stereotype.Service
import java.util.*

@Service
class MotivoExtravioQueryServiceImpl(private val motivoExtravioRepository: MotivoExtravioRepository): MotivoExtravioQueryService {
    override fun buscaPorId(idCarteirinha: String): Optional<MotivoExtravio> {
        return motivoExtravioRepository.findById(idCarteirinha)
    }

    override fun save(motivoExtravio: MotivoExtravio): MotivoExtravio {
        return motivoExtravioRepository.save(motivoExtravio);
    }
}

