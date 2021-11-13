package br.unipar.plano.domain.centrais.services.impl

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.services.CentralApplicationService
import br.unipar.plano.interfaces.rest.centrais.CentralDTO
import org.springframework.stereotype.Service

@Service
class CentralApplicationServiceImpl : CentralApplicationService {

    override fun criar(centralDTO: CentralDTO): IdCentral {
        return IdCentral()
    }

}