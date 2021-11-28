package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.centrais.model.Transporte
import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.usecases.AutorizaTransporteUseCase
import org.springframework.stereotype.Service

@Service
class AutorizaTransporteUseCaseImpl(private val transporteRepository: TransporteRepository) : AutorizaTransporteUseCase {
    override fun executa(transporte: Transporte): Transporte {
        return transporteRepository.save(transporte)
    }

}