package br.unipar.plano.domain.cobrancas.usecases.impl

import br.unipar.plano.domain.cobrancas.commands.CancelarCobrancaCommand
import br.unipar.plano.domain.cobrancas.gateway.CobrancaGateway
import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.cobrancas.usecases.CancelarCobrancaUseCase
import br.unipar.plano.infra.cobrancas.model.toCobranca
import org.springframework.stereotype.Service

@Service
class CancelarCobrancaUseCaseImpl(
    private val queryService: CobrancaQueryService,
    private val gateway: CobrancaGateway
) : CancelarCobrancaUseCase {
    override fun executa(command: CancelarCobrancaCommand): Cobranca {
        val cobrancaBanco = queryService.buscaPorId(command.idContrato, command.idCobranca)

        return gateway.salvar(cobrancaBanco.toCobranca().cancelar())
    }
}