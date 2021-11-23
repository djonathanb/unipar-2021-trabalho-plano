package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.Transporte
import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.usecases.CriaCentralUseCase
import br.unipar.plano.domain.centrais.usecases.CriaTransporteUseCase
import org.springframework.stereotype.Service

@Service
class CriaTransporteUseCaseImpl(private val transporteRepository: TransporteRepository) : CriaTransporteUseCase {
    override fun executa(transporte: Transporte): Transporte {
        return transporteRepository.save(transporte)
    }

}