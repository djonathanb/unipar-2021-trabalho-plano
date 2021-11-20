package br.unipar.plano.interfaces.rest.cobrancas

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.IdCobranca
import org.springframework.http.ResponseEntity
import java.util.*

interface CobrancaResource {

    fun buscaTodos(): ResponseEntity<List<CobrancaSummaryDTO>>
    fun buscaPorId(id: IdCobranca): ResponseEntity<CobrancaDetailsDTO>
    fun registrarCobranca(dto: RegistrarCobrancaDTO): ResponseEntity<Void>
    fun cancelarCobranca(id: IdCobranca): ResponseEntity<CobrancaDetailsDTO>

}