package br.unipar.plano.domain.cobrancas.repository

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.Contrato
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.lang.Nullable
import java.time.LocalDate


interface CobrancaRepository : JpaRepository<Cobranca, IdCobranca> {
    @Query(value = "select count(c)> 0  from Cobranca c where c.contrato = :contrato and month(c.dataEmissao) = month(:emissao) and year(c.dataEmissao) = year(:emissao) and c.status != :status ")
    fun existsInMonthByContratoAndDateAndStatusNotEquals(
        @Param("contrato") contrato: Contrato,
        @Param("emissao") data: LocalDate,
        @Param("status") status: StatusCobranca
    ): Boolean

    @Query(value = "select c from Cobranca c where c.contrato = :contrato and (:status is null or c.status in (:status))")
    fun findAllByContratoAndStatusIn(
        @Param("contrato") contrato: Contrato,
        @Nullable @Param("status") status: List<StatusCobranca>
    ): List<Cobranca>
}