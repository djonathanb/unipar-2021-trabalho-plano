package br.unipar.plano.domain.cobrancas.service

import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.infra.cobrancas.model.CobrancaView
import java.time.LocalDate
import java.util.*

interface CobrancaQueryService {
    fun lista(idContrato: IdContrato): List<CobrancaView>
    fun buscaPorId(idContrato: IdContrato, idCobranca: IdCobranca): CobrancaView
    fun salvar(cobranca: CobrancaView): CobrancaView
    fun buscarPorContratoAndStatus(idContrato: IdContrato, status: Optional<List<StatusCobranca>>): List<CobrancaView>
    fun verificaSeExisteCobrancaProContratoNoMes(
        idContrato: IdContrato,
        dataEmissao: LocalDate,
        statusCobranca: StatusCobranca
    ): Boolean
}
