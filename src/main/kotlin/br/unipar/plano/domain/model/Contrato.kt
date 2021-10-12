package br.unipar.plano.domain.model

import java.util.*

class Contrato(
    val id: UUID,
    val procedimentos: Iterable<Procedimento>,
    val cirurgias: Iterable<Cirurgia>,
    val dependentes: Iterable<Usuario>
)