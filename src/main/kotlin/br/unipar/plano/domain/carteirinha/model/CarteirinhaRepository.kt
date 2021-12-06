package br.unipar.plano.domain.carteirinha.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CarteirinhaRepository : JpaRepository<Carteirinha, String> {
    @Query("select c from Carteirinha c where c.idUsuario = ?1 and (c.status = 'VALIDA' or c.status = 'ENTREGA_PENDENTE')")
    abstract fun findByIdUsuario(idUsuario: Int): Optional<Carteirinha>
}