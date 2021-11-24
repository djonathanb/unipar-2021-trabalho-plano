package br.unipar.plano.interfaces.rest.credenciamentos

import br.unipar.plano.domain.credenciamentos.model.IdEspecialidade
import br.unipar.plano.domain.credenciamentos.model.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.model.Status
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

    data class EspecialidadeSummaryDTO(
        val id: IdEspecialidade,
        val nomeEspecialidade: String
    )

    data class EspecialidadeDetailsDTO(
        val id: IdEspecialidade,
        val especialidadeData: EspDTO
    )

    data class EspDTO(
        @field:NotBlank(message = "O nome deve ser informado")
        val nomeEspecialidade: String
    )
