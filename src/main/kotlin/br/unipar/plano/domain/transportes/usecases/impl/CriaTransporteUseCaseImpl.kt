package br.unipar.plano.domain.centrais.usecases.impl

import br.unipar.plano.domain.transportes.model.Transporte
import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.usecases.CriaTransporteUseCase
import org.springframework.stereotype.Service

@Service
class CriaTransporteUseCaseImpl(private val transporteRepository: TransporteRepository) : CriaTransporteUseCase {

    override fun executa(transporte: Transporte): Transporte {
        if (transporteRepository.existsById(transporte.id)) {
            throw IllegalStateException("Outro Transporte com id ${transporte.id} já foi cadastrado")
        }
        return transporteRepository.save(transporte)
    }

}