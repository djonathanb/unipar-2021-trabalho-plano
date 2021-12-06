package br.unipar.plano.interfaces.rest.carteirinhas

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.carteirinhas.model.IdCarteirinha
import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.EnderecoCentral
import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.model.StatusCentral
import br.unipar.plano.domain.enderecos.model.Endereco
import com.fasterxml.jackson.annotation.JsonValue
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

private const val MIN_NAME_SIZE = 10
private const val MAX_NAME_SIZE = 120

data class CarteirinhaSummaryDTO(
    @field:NotNull(message = "O id deve ser informado")
    val id: IdCarteirinha,
) {
    fun toModel(id: IdCarteirinha) = Carteirinha(
        id = id
    )

    companion object {
        fun toDTO(carteirinha: Carteirinha) = CarteirinhaSummaryDTO(
            id = carteirinha.id,
        )
    }
}