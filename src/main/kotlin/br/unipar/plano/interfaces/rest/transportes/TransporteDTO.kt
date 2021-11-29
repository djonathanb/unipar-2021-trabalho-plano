package br.unipar.plano.interfaces.rest.transportes

import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.transportes.model.TipoTransporte
import br.unipar.plano.interfaces.rest.carteirinhas.CarteirinhaSummaryDTO
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TransporteSummaryDTO(
    val id: IdTransporte,
    val carteirinha: CarteirinhaSummaryDTO,
)

data class TransporteDetailsDTO(
    val id: IdTransporte,
    val transporteData: TransporteDTO
)

data class TransporteDTO(

    @field:NotNull
    val carteirinha: CarteirinhaSummaryDTO,

    @field:NotNull
    val enderecoOrigem: EnderecoTransporteDTO,

    @field:NotNull
    val enderecoDestino: EnderecoTransporteDTO,

    val tipoTransporte: TipoTransporte,

    )

data class EnderecoTransporteDTO(

    @field:NotNull(message = "A cidade deve ser informada")
    @field:Size(min = 1010000)
    val cidade: Int,

    @field:NotNull(message = "O CEP deve ser informado")
    val cep: String,

    @field:NotNull(message = "O bairro deve ser informado")
    val bairro: String,

    @field:NotNull(message = "O logradouro deve ser informado")
    val logradouro: String,

    @field:NotNull(message = "O numero deve ser informado")
    val numero: Int,

    @field:NotNull(message = "O complemento deve ser informado")
    val complemento: String
)