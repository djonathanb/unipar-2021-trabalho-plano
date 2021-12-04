package br.unipar.plano.domain.cobrancas.model

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Plano(
    @Id
    val id: UUID,
    val valorBase: BigDecimal
) {

}
