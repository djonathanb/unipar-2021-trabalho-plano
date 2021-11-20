package br.unipar.plano.domain.centrais.services.impl

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.services.CentralApplicationService
import br.unipar.plano.domain.centrais.services.CentralQueryService
import br.unipar.plano.domain.centrais.usecases.CriaCentralUseCase
import br.unipar.plano.interfaces.rest.centrais.CentralDTO
import br.unipar.plano.interfaces.rest.centrais.CentralDetailsDTO
import br.unipar.plano.interfaces.rest.centrais.CentralSummaryDTO
import org.springframework.stereotype.Service

@Service
class CentralApplicationServiceImpl(
    private val centralQueryService: CentralQueryService,
    private val criaCentralUseCase: CriaCentralUseCase
) : CentralApplicationService {

    override fun cria(centralDTO: CentralDTO): IdCentral {
        val central = centralDTO.toModel(IdCentral(), centralDTO)
        val novaCentral = criaCentralUseCase.executa(central)
        return novaCentral.id
    }

    override fun lista() = centralQueryService.lista().map(CentralSummaryDTO::toDTO)

    override fun buscaPorId(idCentral: IdCentral): CentralDetailsDTO {
        val central = centralQueryService.buscaPorId(idCentral)
        return CentralDetailsDTO.toDTO(central)
    }

}