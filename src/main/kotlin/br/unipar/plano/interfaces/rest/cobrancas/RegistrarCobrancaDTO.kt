package br.unipar.plano.interfaces.rest.cobrancas

import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

data class RegistrarCobrancaDTO(
    @field:PastOrPresent(message = "Não é possível registrar uma Cobrança para uma data futura.")
    @field:NotNull(message = "Não é possível registrar uma Cobrança sem a data de emissão.")
    val dataEmissao: LocalDate,
    val procedimentos: Collection<ProcedimentoDTO>,
    val cirurgias: Collection<CirurgiaDTO>,
)
