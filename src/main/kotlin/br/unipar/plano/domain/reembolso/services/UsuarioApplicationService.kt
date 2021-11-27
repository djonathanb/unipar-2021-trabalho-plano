package br.unipar.plano.domain.reembolso.services

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.interfaces.dto.ReembolsoDTO

interface UsuarioApplicationService {
    fun solicitaReembolso(reembolsoDTO: ReembolsoDTO) : IdReembolso
}
