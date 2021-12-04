package br.unipar.plano.domain.contratos.planos.services.impl

import br.unipar.plano.domain.contratos.planos.model.IdPlano
import br.unipar.plano.domain.contratos.planos.services.PlanoApplicationService
import br.unipar.plano.domain.contratos.planos.services.PlanoQueryService
import br.unipar.plano.domain.contratos.planos.usecases.CriaPlanoUseCase
import br.unipar.plano.domain.contratos.planos.usecases.DeletaPlanoUseCase
import br.unipar.plano.domain.contratos.planos.usecases.*
import br.unipar.plano.interfaces.rest.contratos.planos.PlanoDTO
import br.unipar.plano.interfaces.rest.contratos.planos.PlanoDetailsDTO
import br.unipar.plano.interfaces.rest.contratos.planos.PlanoSummaryDTO
import org.springframework.stereotype.Service

@Service
class PlanoApplicationServiceImpl(
    private val planoQueryService: PlanoQueryService,
    private val addPlanoUseCase: CriaPlanoUseCase,
    private val deletePlanoUseCase: DeletaPlanoUseCase,
) : PlanoApplicationService {

    override fun cria(planoDTO: PlanoDTO): IdPlano {
        val plano = planoDTO.toModel(IdPlano())
        val nPlano = addPlanoUseCase.executa(plano)
        return nPlano.id
    }

    override fun deleta(idPlano: IdPlano) {
        deletePlanoUseCase.executa(idPlano)
    }

    override fun lista() = planoQueryService.lista().map(PlanoSummaryDTO::toDTO)

    override fun buscaPorId(idPlano: IdPlano): PlanoDetailsDTO {
        val plano = planoQueryService.buscaPorId(idPlano)
        return PlanoDetailsDTO.toDTO(plano)
    }

}