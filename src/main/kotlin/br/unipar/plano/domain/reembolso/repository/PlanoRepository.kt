package br.unipar.plano.domain.reembolso.repository

import br.unipar.plano.domain.reembolso.model.IdReembolso
import br.unipar.plano.domain.reembolso.model.Plano
import br.unipar.plano.domain.reembolso.model.Reembolso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlanoRepository : JpaRepository<Reembolso, IdReembolso> {
    @Query("from Plano p inner join Usuario u on p.usuario = u.id")
    fun findByPlano(idUsuario: UUID) : Plano
}