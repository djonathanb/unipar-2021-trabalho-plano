package br.unipar.plano.infra.cobrancas.gateway

import br.unipar.plano.domain.cobrancas.gateway.CobrancaGateway
import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.infra.cobrancas.repository.CobrancaRepository
import org.springframework.stereotype.Service

@Service
class CobrancaGatewayImpl(val cobrancaRepository: CobrancaRepository) : CobrancaGateway {
    override fun salvar(cobranca: Cobranca): Cobranca {
        return cobrancaRepository.save(cobranca)
    }

    override fun remover(cobranca: Cobranca) {
        cobrancaRepository.delete(cobranca)
    }
}