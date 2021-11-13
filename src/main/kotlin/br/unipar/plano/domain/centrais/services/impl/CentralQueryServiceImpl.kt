package br.unipar.plano.domain.centrais.services.impl

import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.services.CentralQueryService
import org.springframework.stereotype.Service

public val centrais = mutableListOf<Central>()

@Service
class CentralQueryServiceImpl : CentralQueryService {

    override fun lista(): List<Central> = centrais
    override fun buscaPorId(idCentral: IdCentral): Central = centrais.first {
        it.id == idCentral
    }

}