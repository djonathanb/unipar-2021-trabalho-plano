package br.unipar.plano.domain.transportes.usecases.impl

import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.usecases.AprovaTransporteUseCase
import br.unipar.plano.domain.transportes.model.Transporte
import org.springframework.stereotype.Service

@Service
class AprovaTransporteUseCaseImpl(private val transporteRepository: TransporteRepository) : AprovaTransporteUseCase {
    override fun executa(transporte: Transporte): Transporte {
        return transporteRepository.save(transporte)
    }

}