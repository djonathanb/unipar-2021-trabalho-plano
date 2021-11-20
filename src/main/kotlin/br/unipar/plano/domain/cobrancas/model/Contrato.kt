package br.unipar.plano.domain.cobrancas.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Contrato(
    @Id
    val id: UUID,
    @OneToMany
    val procedimentos: Collection<Procedimento>,
    @OneToMany
    val cirurgias: Collection<Cirurgia>,
    @OneToMany
    val dependentes: Collection<Usuario>
)