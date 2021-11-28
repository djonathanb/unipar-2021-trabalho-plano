package br.unipar.plano.domain.cobrancas.model

import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Contrato(
    @Id
    val id: UUID,
    @OneToMany(cascade = [CascadeType.ALL])
    val procedimentos: Collection<Procedimento>,
    @OneToMany(cascade = [CascadeType.ALL])
    val cirurgias: Collection<Cirurgia>,
    @OneToMany(cascade = [CascadeType.ALL])
    val dependentes: Collection<Usuario>
)