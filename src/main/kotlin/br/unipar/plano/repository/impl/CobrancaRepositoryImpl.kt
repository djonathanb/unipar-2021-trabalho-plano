package br.unipar.plano.repository.impl

import br.unipar.plano.domain.model.Cobranca
import br.unipar.plano.domain.model.Contrato
import br.unipar.plano.domain.valueobjects.StatusCobranca
import br.unipar.plano.repository.CobrancaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*
import kotlin.NoSuchElementException
import kotlin.collections.ArrayList

@Repository
class CobrancaRepositoryImpl : CobrancaRepository {
    private var dados: MutableList<Cobranca> = ArrayList()

    override fun findAll(): MutableIterable<Cobranca> = dados

    override fun findById(id: UUID): Cobranca = dados.first { it.id == id }

    override fun save(cobranca: Cobranca): Cobranca {
        val index = dados.indexOfFirst { it.id == cobranca.id }
        if (index == -1)
            dados.add(cobranca)
        else
            dados[index] = cobranca
        return cobranca
    }

    override fun existsInMonthByContratoAndByDateAndByStatusNotEquals(contrato: Contrato, data: LocalDate, status: StatusCobranca): Boolean {
        return try {
            dados.first {
                it.contrato.id == contrato.id &&
                        it.dataEmissao.month == data.month &&
                        it.dataEmissao.year == data.year &&
                        it.status != status
            }
            true
        } catch (e: NoSuchElementException) {
            false
        }
    }
}