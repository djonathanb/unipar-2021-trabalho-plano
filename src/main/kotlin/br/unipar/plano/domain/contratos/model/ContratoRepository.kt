package br.unipar.plano.domain.contratos.model

import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.planos.model.Plano
import com.sun.istack.Nullable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ContratoRepository : JpaRepository<Contrato, IdContrato> {

    fun findByPlano (idPlano: Plano): List<Contrato>

    @Query(value = "select c from Contrato c where c.plano = :plano and (:status is null or c.status in (:status))")
    fun findbyPlanoeStatus(
        @Param("plano") plano: Plano,
        @Nullable @Param("status") statusContrato: List<StatusContrato>): List<Contrato>

    fun findByTitularAndStatus (titular: Pessoa, statusContrato: StatusContrato): List<Contrato>
}