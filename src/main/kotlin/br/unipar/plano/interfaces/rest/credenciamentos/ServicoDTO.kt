package br.unipar.plano.interfaces.rest.credenciamentos

import br.unipar.plano.domain.credenciamentos.model.IdServico
import javax.validation.constraints.NotBlank

data class ServicoSummaryDTO(
    val id: IdServico,
    val servico: String
)

data class ServicoDetailsDTO(
    val id: IdServico,
    val servicoData: ServicoDTO
)

data class ServicoDTO(
    @field:NotBlank(message = "O servico deve ser informado")
    val servico: String
)

