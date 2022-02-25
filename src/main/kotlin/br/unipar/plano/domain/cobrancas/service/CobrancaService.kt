package br.unipar.plano.domain.cobrancas.service

import br.unipar.plano.domain.cobrancas.model.Cirurgia
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.model.Procedimento
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.interfaces.rest.cobrancas.CobrancaDetailsDTO
import br.unipar.plano.interfaces.rest.cobrancas.CobrancaSummaryDTO
import java.time.LocalDate
import java.util.*

interface CobrancaService {

    fun registrarCobranca(
        idContrato: IdContrato, dataEmissao: LocalDate, cirurgias: Collection<Cirurgia>,
        procedimentos: Collection<Procedimento>
    ): IdCobranca

    fun cancelarCobranca(idContrato: IdContrato, idCobranca: IdCobranca): CobrancaDetailsDTO
    fun buscaTodos(idContrato: IdContrato): List<CobrancaSummaryDTO>
    fun buscarPorId(idContrato: IdContrato, id: IdCobranca): CobrancaDetailsDTO
    fun buscarPorContratoAndStatus(
        idContrato: IdContrato,
        status: Optional<List<StatusCobranca>>
    ): List<CobrancaDetailsDTO>
}