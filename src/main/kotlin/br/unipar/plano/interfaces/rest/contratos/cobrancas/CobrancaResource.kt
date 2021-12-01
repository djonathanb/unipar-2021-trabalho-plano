package br.unipar.plano.interfaces.rest.contratos.cobrancas

import org.springframework.http.ResponseEntity
import java.util.*

interface CobrancaResource {

    fun buscaTodos(): ResponseEntity<List<CobrancaSummaryDTO>>
    fun buscaPorId(id: UUID): ResponseEntity<CobrancaDetailsDTO>
    fun cancelarCobranca(id: UUID): ResponseEntity<CobrancaDetailsDTO>

}