package br.unipar.plano.application.service.interfaces

import br.unipar.plano.application.exception.NegocioException
import br.unipar.plano.application.exception.RegistroNaoEncontradoException
import br.unipar.plano.domain.model.Cobranca
import br.unipar.plano.domain.model.Contrato
import java.time.LocalDate
import java.util.*
import kotlin.jvm.Throws

interface CobrancaService {
    @Throws(NegocioException::class)
    fun registrarCobranca(contrato: Contrato, dataEmissao: LocalDate): Cobranca
    @Throws(RegistroNaoEncontradoException::class)
    fun cancelarCobranca(cobranca: Cobranca): Cobranca
    fun buscaTodos(): MutableIterable<Cobranca>
    @Throws(RegistroNaoEncontradoException::class)
    fun buscarPorId(id: UUID): Cobranca
}