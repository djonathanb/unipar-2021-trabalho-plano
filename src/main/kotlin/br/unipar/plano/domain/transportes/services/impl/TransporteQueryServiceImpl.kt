package br.unipar.plano.domain.centrais.services.impl

import br.unipar.plano.domain.centrais.model.*
import br.unipar.plano.domain.centrais.services.TransporteQueryService
import org.springframework.stereotype.Service

@Service
class TransporteQueryServiceImpl(private val transporteRepository: TransporteRepository) : TransporteQueryService {

    override fun lista(): List<Transporte> = transporteRepository.findAll()

    override fun buscaPorId(idTransporte: IdTransporte): Transporte = transporteRepository.findById(idTransporte).orElseThrow {
        Exception("Transporte com id ${idTransporte.id} não encontrada")
    }

}