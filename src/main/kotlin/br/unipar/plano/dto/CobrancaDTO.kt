package br.unipar.plano.dto

import br.unipar.plano.domain.model.Contrato
import java.time.LocalDate

data class CobrancaDTO(val contrato: Contrato, val dataEmissao: LocalDate)
