package br.unipar.plano.domain.carteirinha.model

import java.util.*
import javax.persistence.*

@Entity
class Carteirinha(
        @field:Id
        val numeroCarteirinha: String,

        @Column(nullable = false)
        val idUsuario: Int,

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

        if (Date() <= dataEmissao)
            throw Exception("Não é possível registrar a carteirinha com data de entrega menor que data de emissão")

        return copy(status = StatusCarteirinha.VALIDA, dataEntrega = Date());
    }

    fun registrarExtravio(): Carteirinha {
        return copy(status = StatusCarteirinha.EXTRAVIADA);
    }

    fun validate() : Boolean {
        return (status == StatusCarteirinha.VALIDA && dataVencimento >= Date())
    }

    private fun copy(
            numeroCarteirinha: String = this.numeroCarteirinha,
            idUsuario: Int = this.idUsuario,
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
