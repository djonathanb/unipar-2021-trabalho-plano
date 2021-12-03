package br.unipar.plano.domain.reembolso.repository

import br.unipar.plano.domain.reembolso.model.Carteirinha
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CarteirinhaRepository : JpaRepository<Carteirinha, UUID> {
    @Query("from Carteirinha c where c.usuario.id = ?1")
    fun findByCarteirinha(idUsuario: UUID) : Carteirinha
}