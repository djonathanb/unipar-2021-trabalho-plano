package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.Transporte
import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.usecases.CancelarTransporteUseCase
import org.springframework.stereotype.Service

@Service
class CancelarTransporteUseCaseImpl(private val transporteRepository: TransporteRepository) : CancelarTransporteUseCase {
    override fun executa(transporte: Transporte): Transporte {
        return transporteRepository.save(transporte)
    }

}