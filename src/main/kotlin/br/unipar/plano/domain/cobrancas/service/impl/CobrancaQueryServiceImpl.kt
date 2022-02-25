package br.unipar.plano.domain.cobrancas.service.impl

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.repository.CobrancaRepository
import br.unipar.plano.domain.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.springframework.stereotype.Service
import java.util.*

@Service
class CobrancaQueryServiceImpl(private val repository: CobrancaRepository) : CobrancaQueryService {

    override fun lista(idContrato: IdContrato): List<Cobranca> = repository.findAll(idContrato)

    override fun buscaPorId(idContrato: IdContrato, idCobranca: IdCobranca): Cobranca =
        repository.findById(idContrato, idCobranca).orElseThrow {
            CobrancaNotFoundException(idCobranca)
        }

    override fun buscarPorContratoAndStatus(
        idContrato: IdContrato,
        status: Optional<List<StatusCobranca>>
    ): List<Cobranca> {
        return repository.findAllByContratoAndStatusIn(idContrato, status.orElse(null))
    }

}