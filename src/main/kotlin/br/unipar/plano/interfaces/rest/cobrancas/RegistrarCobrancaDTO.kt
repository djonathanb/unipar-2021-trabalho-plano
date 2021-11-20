package br.unipar.plano.interfaces.rest.cobrancas

import br.unipar.plano.domain.cobrancas.model.Contrato
import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

data class RegistrarCobrancaDTO(
    @field:NotNull(message = "É necessário informar um contrato.")
    val contrato: Contrato,
    @PastOrPresent(message = "Não é possível registrar uma Cobrança para uma data futura.")
    val dataEmissao: LocalDate
)
