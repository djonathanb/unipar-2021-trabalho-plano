package br.unipar.plano.domain.cobrancas.repository

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import java.util.*


interface CobrancaRepository : JpaRepository<Cobranca, IdCobranca> {
    @Query(value = "select true from Cobranca c where c.contrato = :contrato and month(c.dataEmissao) = month(:emissao) and year(c.dataEmissao) = year(:emissao) and c.status != :status ")
    fun existsInMonthByContratoAndByDateAndByStatusNotEquals(@Param("contrato") contrato: Contrato, @Param("emissao") data: LocalDate, @Param("status") status: StatusCobranca): Optional<Boolean>
    fun findAllByContratoAndStatusIn(contrato: Contrato, status: Optional<StatusCobranca>) : List<Cobranca>
}