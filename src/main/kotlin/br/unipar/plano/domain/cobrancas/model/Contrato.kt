package br.unipar.plano.domain.cobrancas.model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Contrato(
    @Id
    val id: IdContrato,
    @OneToMany(cascade = [CascadeType.ALL])
    val dependentes: Collection<Usuario>
)