package br.unipar.plano.domain.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class Usuario(
    val id: UUID,
    val plano: Plano,
    private val dataNascimento: LocalDate
) {
    fun idade() = ChronoUnit.YEARS.between(dataNascimento, LocalDate.now())
}