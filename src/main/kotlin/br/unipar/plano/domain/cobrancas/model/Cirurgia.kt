package br.unipar.plano.domain.cobrancas.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Cirurgia(
    @Id val id: UUID,
    @ManyToOne val cobranca: Cobranca? = null
) {
}