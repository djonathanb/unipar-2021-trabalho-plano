package br.unipar.plano.domain.carteirinha.services

import br.unipar.plano.domain.carteirinha.model.MotivoExtravio
import org.springframework.stereotype.Service
import java.util.*

@Service
interface MotivoExtravioQueryService {
    fun buscaPorId(idCarteirinha: String): Optional<MotivoExtravio>
    fun save(motivoExtravio: MotivoExtravio): MotivoExtravio
}