package br.unipar.plano.domain.centrais.model

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.transportes.model.EnderecoTransporte
import java.time.LocalDate
import javax.persistence.*

enum class StatusTransporte {
    PENDENTE, APROVADO, CANCELADO;
}

enum class TipoTransporte(val tipo: String) {
    AMBULANCIA("Ambulância"),
    UTI_MOVEL("UTI Móvel"),
    AEREO("Aéreo");}

@Entity
class Transporte(
    @field:EmbeddedId
    val id: IdTransporte,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    val carteirinha: Carteirinha,

    @Column
    val dataSolicitacao: LocalDate = LocalDate.now(),

    @Enumerated(EnumType.STRING)
    val status: StatusTransporte = StatusTransporte.PENDENTE,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    val enderecoOrigem: EnderecoTransporte,

    @OneToOne(cascade = [CascadeType.DETACH], orphanRemoval = true)
    val enderecoDestino: EnderecoTransporte,

    @Enumerated(EnumType.STRING)
    val tipoTransporte: TipoTransporte

) {

    fun cancela(): Transporte {
        if (status == StatusTransporte.CANCELADO) {
            throw Exception("Não é possível cancelar um Transporte com status $status")
        }
        return copy(status = StatusTransporte.CANCELADO)
    }

    fun autoriza(): Transporte {
        if (status != StatusTransporte.PENDENTE) {
            throw Exception("Não é possível aprovar um Transporte com status $status")
        }
        return copy(status = StatusTransporte.APROVADO)
    }

    fun with(
        id: IdTransporte = this.id,
        carteirinha: Carteirinha = this.carteirinha,
        dataSolicitacao: LocalDate = this.dataSolicitacao,
        status: StatusTransporte = this.status,
        enderecoOrigem: EnderecoTransporte = this.enderecoOrigem,
        enderecoDestino: EnderecoTransporte = this.enderecoDestino,
        tipoTransporte: TipoTransporte = this.tipoTransporte
    ) = copy(
        id = id,
        carteirinha = carteirinha,
        dataSolicitacao = dataSolicitacao,
        status = status,
        enderecoOrigem = enderecoOrigem,
        enderecoDestino = enderecoDestino,
        tipoTransporte = tipoTransporte
    )

    private fun copy(
        id: IdTransporte = this.id,
        carteirinha: Carteirinha = this.carteirinha,
        dataSolicitacao: LocalDate = this.dataSolicitacao,
        status: StatusTransporte = this.status,
        enderecoOrigem: EnderecoTransporte = this.enderecoOrigem,
        enderecoDestino: EnderecoTransporte = this.enderecoDestino,
        tipoTransporte: TipoTransporte = this.tipoTransporte
    ) = Transporte(
        id = id,
        carteirinha = carteirinha,
        dataSolicitacao = dataSolicitacao,
        status = status,
        enderecoOrigem = enderecoOrigem,
        enderecoDestino = enderecoDestino,
        tipoTransporte = tipoTransporte
    )
}
