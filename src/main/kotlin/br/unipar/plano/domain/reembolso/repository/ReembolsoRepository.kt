package br.unipar.plano.domain.reembolso.repository

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.model.IdUsuario
import br.unipar.plano.domain.reembolso.model.Reembolso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.time.LocalDate

@Repository
interface ReembolsoRepository : JpaRepository<Reembolso, IdReembolso> {
    @Query("select count(r.id) from Reembolso r where r.usuario = :idUsuario and r.dataSolicitacao >= :dataSolicitacao")
    fun totalSolicitacoesAno(
            @Param("idUsuario") idUsuario: IdUsuario,
            @Param("dataSolicitacao") dataSolicitacao: LocalDate) : Long

    @Query("select sum(r.valor) from Reembolso r where r.usuario = :idUsuario and r.dataSolicitacao >= :dataSolicitacao")
    fun valorTotalSolicitacoesAno(
            @Param("idUsuario") idUsuario: IdUsuario,
            @Param("dataSolicitacao") anoSolicitacao: LocalDate) : BigDecimal

//    @Query("select count(r.id) as Reembolso from Reembolso r where r.usuario = ?1 and extract(year from r.data) = ?2")
//    fun totalSolicitacoesAno(idUsuario: IdUsuario, year: Int) : Long

//    @Query("select sum(r.valor) from Reembolso r where r.usuario = ?1 and extract(year from r.data) = ?2")
//    fun valorTotalSolicitacoesRealizadas(idUsuario: IdUsuario, year:Int) : BigDecimal
}

