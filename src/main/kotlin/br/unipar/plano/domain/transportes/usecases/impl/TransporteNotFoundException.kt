package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.Transporte
import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.usecases.AprovaTransporteUseCase
import org.springframework.stereotype.Service

@Service
class AprovaTransporteUseCaseImpl2(private val transporteRepository: TransporteRepository) : AprovaTransporteUseCase {
    override fun executa(transporte: Transporte): Transporte {
        return transporteRepository.save(transporte)
    }

}