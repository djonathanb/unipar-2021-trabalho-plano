package br.unipar.plano.interfaces.rest.prenatais

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.prenatal.model.IdPreNatal
import br.unipar.plano.domain.prenatal.model.StatusAtendimento
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

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

    @field:NotNull(message = "A data da solicitação deve ser informada")
    val dataSolicitacao: LocalDate,

    @field:NotNull(message = "Campo não pode ser nulo")
    val temObstetricia: Boolean,

    @field:NotNull(message = "Status de atendimento deve ser informado")
    val status: StatusAtendimento,

    )