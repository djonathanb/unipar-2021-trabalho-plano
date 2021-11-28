package br.unipar.plano.domain.centrais.services.impl

import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.centrais.model.Transporte
import br.unipar.plano.domain.centrais.model.TransporteRepository
import br.unipar.plano.domain.centrais.services.TransporteQueryService
import br.unipar.plano.domain.transportes.usecases.impl.TransporteNotFoundException
import org.springframework.stereotype.Service

@Service
class TransporteQueryServiceImpl(private val transporteRepository: TransporteRepository) : TransporteQueryService {

    override fun lista(): List<Transporte> = transporteRepository.findAll()

    override fun buscaPorId(idTransporte: IdTransporte): Transporte = transporteRepository.findById(idTransporte).orElseThrow {
        TransporteNotFoundException(idTransporte);
    }

}