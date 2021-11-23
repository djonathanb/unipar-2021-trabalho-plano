package br.unipar.plano.interfaces.rest.transportes

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.centrais.model.TipoTransporte
import br.unipar.plano.interfaces.rest.centrais.EnderecoDTO
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TransporteSummaryDTO(
    val id: IdTransporte,
    val carteirinha: Carteirinha,
)

data class TransporteDetailsDTO(
    val id: IdTransporte,
    val transporteData: TransporteDTO
)

data class TransporteDTO(

    @field:NotNull
    val carteirinha: Carteirinha,

    @field:NotNull
    val enderecoOrigem: EnderecoTransporteDTO,

    @field:NotNull
    val enderecoDestino: EnderecoTransporteDTO,

    val tipoTransporte: TipoTransporte,

    )
data class EnderecoTransporteDTO(

    @field:NotNull
    @field:Size(min = 1010000)
    val cidade: Int,

    val cep: String,
    val bairro: String,
    val logradouro: String,
    val numero: Int,
    val complemento: String
)