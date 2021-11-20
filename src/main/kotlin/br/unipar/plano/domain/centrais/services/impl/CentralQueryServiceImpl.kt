package br.unipar.plano.domain.centrais.services.impl

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.CentralRepository
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.services.CentralQueryService
import br.unipar.plano.domain.centrais.usecases.impl.CentralNotFoundException
import org.springframework.stereotype.Service

@Service
class CentralQueryServiceImpl(private val centralRepository: CentralRepository) : CentralQueryService {

    override fun lista(): List<Central> = centralRepository.findAll()

    override fun buscaPorId(idCentral: IdCentral): Central = centralRepository.findById(idCentral).orElseThrow {
        CentralNotFoundException(idCentral)
    }

}