package br.unipar.plano.domain.cobrancas.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Usuario(
    @Id val id: UUID,
    @ManyToOne
    val plano: Plano,
    private val dataNascimento: LocalDate
) {
    fun idade() = ChronoUnit.YEARS.between(dataNascimento, LocalDate.now())
}