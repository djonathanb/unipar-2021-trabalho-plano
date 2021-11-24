package br.unipar.plano.interfaces.rest.prenatal

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.prenatal.model.IdPreNatal
import br.unipar.plano.domain.prenatal.model.StatusAtendimento
import java.time.LocalDate
import javax.validation.constraints.NotBlank

data class PreNatalSummaryDTO(
    val id: IdPreNatal,
    val carteirinha: Carteirinha,
)

data class PreNatalDetailsDTO(
    val id: IdPreNatal,
    val transporteData: PreNatalDTO
)

data class PreNatalDTO(

    @field:NotBlank(message = "A carteirinha deve ser informada")
    val carteirinha: Carteirinha,

    val dataSolicitacao: LocalDate,

    val temObstetricia: Boolean,

    val status: StatusAtendimento,

    )