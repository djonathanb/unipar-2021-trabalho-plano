package br.unipar.plano.repository

import br.unipar.plano.domain.model.Cobranca
import br.unipar.plano.domain.model.Contrato
import br.unipar.plano.domain.valueobjects.StatusCobranca
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*


interface CobrancaRepository {
    fun findAll(): MutableIterable<Cobranca>
    fun findById(id: UUID): Cobranca
    fun save(cobranca: Cobranca): Cobranca
    fun existsInMonthByContratoAndByDateAndByStatusNotEquals(contrato: Contrato, data: LocalDate, status: StatusCobranca): Boolean
}