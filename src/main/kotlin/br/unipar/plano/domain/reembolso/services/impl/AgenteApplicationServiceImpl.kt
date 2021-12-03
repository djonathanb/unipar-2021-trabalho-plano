package br.unipar.plano.domain.reembolso.services.impl

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.services.AgenteApplicationService
import br.unipar.plano.domain.reembolso.services.ReembolsoQueryService
import br.unipar.plano.domain.reembolso.usecases.AutorizaReembolsoUseCase
import br.unipar.plano.domain.reembolso.usecases.RejeitaReembolsoUseCase
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoDetailsDTO
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoRejeitadoDTO
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoSummaryDTO
import org.springframework.stereotype.Service

@Service
class AgenteApplicationServiceImpl(
        private val autorizaReembolsoUseCase: AutorizaReembolsoUseCase,
        private val rejeitaReembolsoUseCase: RejeitaReembolsoUseCase,
        private val reembolsoQueryService: ReembolsoQueryService
) : AgenteApplicationService {

    override fun autorizarReembolso(idReembolso: IdReembolso) {
        autorizaReembolsoUseCase.executa(idReembolso)
    }

    override fun rejeitarReembolso(idReembolso: IdReembolso, reembolsoRejeitadoDTO: ReembolsoRejeitadoDTO) {
        val reembolsoRejeitado = reembolsoRejeitadoDTO.toModel(reembolsoRejeitadoDTO.id)
        rejeitaReembolsoUseCase.executa(idReembolso, reembolsoRejeitado)
    }

    override fun lista() = reembolsoQueryService.lista().map(ReembolsoSummaryDTO::toDTO)

    override fun buscaPorId(idReembolso: IdReembolso): ReembolsoDetailsDTO {
        val reembolso = reembolsoQueryService.buscaPorId(idReembolso)
        return ReembolsoDetailsDTO.toDTO(reembolso)
    }

}