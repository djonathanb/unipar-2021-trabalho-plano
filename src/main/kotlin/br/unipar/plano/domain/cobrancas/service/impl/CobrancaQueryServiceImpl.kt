package br.unipar.plano.domain.cobrancas.service.impl

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.repository.CobrancaRepository
import br.unipar.plano.domain.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.springframework.stereotype.Service
import java.util.*

@Service
class CobrancaQueryServiceImpl(private val repository: CobrancaRepository) : CobrancaQueryService {

    override fun lista(): List<Cobranca> = repository.findAll()

    override fun buscaPorId(idCobranca: IdCobranca): Cobranca = repository.findById(idCobranca).orElseThrow {
        CobrancaNotFoundException(idCobranca)
    }

    override fun buscarPorContratoAndStatus(
        contrato: Contrato,
        status: Optional<List<StatusCobranca>>
    ): List<Cobranca> {
        return repository.findAllByContratoAndStatusIn(contrato, status.orElse(null))
    }

}