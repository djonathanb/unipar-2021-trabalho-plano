package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.transportes.model.Transporte
import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.usecases.CancelaTransporteUseCase
import org.springframework.stereotype.Service

@Service
class CancelaTransporteUseCaseImpl(private val transporteRepository: TransporteRepository) : CancelaTransporteUseCase {
    override fun executa(transporte: Transporte): Transporte {
        return transporteRepository.save(transporte)
    }

}