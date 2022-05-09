package br.unipar.plano.domain.cobrancas.service.impl

import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.infra.cobrancas.model.CobrancaView
import br.unipar.plano.infra.cobrancas.repository.CobrancaQueryRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class CobrancaQueryServiceImpl(private val repository: CobrancaQueryRepository) : CobrancaQueryService {

    override fun lista(idContrato: IdContrato): List<CobrancaView> = repository.findAll(idContrato)

    override fun buscaPorId(idContrato: IdContrato, idCobranca: IdCobranca): CobrancaView =
        repository.findById(idContrato, idCobranca).orElseThrow {
            CobrancaNotFoundException(idCobranca)
        }

    override fun salvar(cobranca: CobrancaView): CobrancaView {
        return repository.save(cobranca)
    }

    override fun buscarPorContratoAndStatus(
        idContrato: IdContrato,
        status: Optional<List<StatusCobranca>>
    ): List<CobrancaView> {
        return repository.findAllByContratoAndStatusIn(idContrato, status.orElse(null))
    }

    override fun verificaSeExisteCobrancaProContratoNoMes(
        idContrato: IdContrato,
        dataEmissao: LocalDate,
        statusCobranca: StatusCobranca
    ): Boolean {
        return repository.existsInMonthByContratoAndDateAndStatusNotEquals(idContrato, dataEmissao, statusCobranca)
    }

}