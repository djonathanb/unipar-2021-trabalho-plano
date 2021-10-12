package br.unipar.plano.resource

import br.unipar.plano.domain.model.Cobranca
import br.unipar.plano.dto.CobrancaDTO
import org.springframework.http.ResponseEntity
import java.util.*

interface CobrancaResource {

    fun buscaTodos(): ResponseEntity<MutableIterable<Cobranca>>
    fun buscaPorId(id: UUID): ResponseEntity<Cobranca>
    fun registrarCobranca(dto: CobrancaDTO): ResponseEntity<Void>
    fun cancelarCobranca(id: UUID): ResponseEntity<Cobranca>

}