package br.unipar.plano.interfaces.rest.cobrancas

import org.springframework.http.ResponseEntity
import java.util.*

interface CobrancaResource {

    fun buscaTodos(idContrato: UUID): ResponseEntity<List<CobrancaSummaryDTO>>
    fun buscaPorId(idContrato: UUID, id: UUID): ResponseEntity<CobrancaDetailsDTO>
    fun registrarCobranca(idContrato: UUID, dto: RegistrarCobrancaDTO): ResponseEntity<Void>
    fun cancelarCobranca(idContrato: UUID, id: UUID): ResponseEntity<CobrancaDetailsDTO>

}