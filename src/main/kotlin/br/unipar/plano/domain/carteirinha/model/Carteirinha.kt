package br.unipar.plano.domain.carteirinha.model

import br.unipar.plano.domain.usuario.IdUsuario
import java.util.*
import javax.persistence.*

@Entity
class Carteirinha(
        @field:EmbeddedId
        val numeroCarteirinha: IdCarteirinha,

        @Column(nullable = false)
        val idUsuario: IdUsuario,

        @Column(nullable = false)
        val dataEmissao: Date,

        @Column(nullable = false)
        val dataVencimento: Date,

        @Column()
        val dataEntrega: Date?,

        @Enumerated(EnumType.STRING)
        val status: StatusCarteirinha) {

    fun registrarEntrega(): Carteirinha {

        if (status != StatusCarteirinha.ENTREGA_PENDENTE)
            throw Exception("Não é possível entregar uma carteirinha que não esteja pendente")

        return copy(status = StatusCarteirinha.VALIDA, dataEntrega = Date());
    }

    fun registrarExtravio(motivo: String): Carteirinha {
        val m = MotivoExtravio(dataExtravio = Date(), numeroCarteirinha = this.numeroCarteirinha, motivoExtravio = motivo);

        return copy(status = StatusCarteirinha.EXTRAVIADA);
    }

    fun validate() : Boolean {
        return (status == StatusCarteirinha.VALIDA && dataVencimento >= Date())
    }

    private fun copy(
            numeroCarteirinha: IdCarteirinha = this.numeroCarteirinha,
            idUsuario: IdUsuario = this.idUsuario,
            dataEmissao: Date = this.dataEmissao,
            dataVencimento: Date = this.dataVencimento,
            dataEntrega: Date? = this.dataEntrega,
            status: StatusCarteirinha = this.status
    ) = Carteirinha(
            numeroCarteirinha = numeroCarteirinha,
            idUsuario = idUsuario,
            dataEmissao = dataEmissao,
            dataVencimento = dataVencimento,
            dataEntrega = dataEntrega,
            status = status
    )


}