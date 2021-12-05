package br.unipar.plano.domain.reembolso.services.impl

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.model.StatusReembolso
import br.unipar.plano.domain.reembolso.services.UsuarioApplicationService
import br.unipar.plano.domain.reembolso.usecases.SolicitaReembolsoUseCase
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoDTO
import org.springframework.stereotype.Service

@Service
class UsuarioApplicationServiceImpl(
        private val solicitaReembolsoUseCase: SolicitaReembolsoUseCase
) : UsuarioApplicationService {

    override fun solicitaReembolso(reembolsoDTO: ReembolsoDTO): IdReembolso {
        val reembolso = reembolsoDTO.toModel(IdReembolso())

        reembolso.statusReembolso = StatusReembolso.EM_ANALIZE

        val novoReembolso = solicitaReembolsoUseCase.executa(reembolso)

        return novoReembolso.id
    }
}