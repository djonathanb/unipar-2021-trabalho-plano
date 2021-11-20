package br.unipar.plano.domain.cobrancas.usecases.impl

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.repository.CobrancaRepository
import br.unipar.plano.domain.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.cobrancas.usecases.CancelarCobrancaUseCase
import org.springframework.stereotype.Service

@Service
class CancelarCobrancaUseCaseImpl(private val queryService: CobrancaQueryService, private val repository: CobrancaRepository) : CancelarCobrancaUseCase {
    override fun executa(idCobranca: IdCobranca): Cobranca {
        val cobrancaBanco = queryService.buscaPorId(idCobranca)
        cobrancaBanco.cancelar()
        return repository.save(cobrancaBanco)
    }
}