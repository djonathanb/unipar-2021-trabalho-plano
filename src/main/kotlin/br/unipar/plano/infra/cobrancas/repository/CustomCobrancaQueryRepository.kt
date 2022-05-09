package br.unipar.plano.infra.cobrancas.repository

import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.infra.cobrancas.model.CobrancaView
import java.time.LocalDate
import java.util.*

interface CustomCobrancaQueryRepository {
    fun existsInMonthByContratoAndDateAndStatusNotEquals(
        idContrato: IdContrato,
        data: LocalDate,
        status: StatusCobranca
    ): Boolean

    fun findAllByContratoAndStatusIn(
        idContrato: IdContrato,
        status: List<StatusCobranca>
    ): List<CobrancaView>

    fun findAll(idContrato: IdContrato): List<CobrancaView>

    fun findById(idContrato: IdContrato, idCobranca: IdCobranca): Optional<CobrancaView>
}