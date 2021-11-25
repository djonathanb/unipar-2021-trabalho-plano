package br.unipar.plano.interfaces.rest.credenciamentos

import br.unipar.plano.domain.credenciamentos.model.IdEspecialidade
import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.IdServico
import br.unipar.plano.domain.credenciamentos.model.Status
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

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

