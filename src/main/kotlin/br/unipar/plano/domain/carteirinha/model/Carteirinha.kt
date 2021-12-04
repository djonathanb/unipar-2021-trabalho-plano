package br.unipar.plano.domain.carteirinha.model

import java.time.LocalDate
import javax.persistence.*

@Entity
class Carteirinha(
        @field:Id
        val numeroCarteirinha: String,

        @Column(nullable = false)
        val idUsuario: Int,

        @Column(nullable = false)
        val dataEmissao: LocalDate,

        @Column(nullable = false)
        val dataVencimento: LocalDate,

        @Column(nullable = true)
        val dataEntrega: LocalDate?,

        @Enumerated(EnumType.STRING)
        val status: StatusCarteirinha = StatusCarteirinha.ENTREGA_PENDENTE) {

    fun registrarEntrega(): Carteirinha {

        if (status != StatusCarteirinha.ENTREGA_PENDENTE)
            throw Exception("Não é possível entregar uma carteirinha que não esteja pendente")

        if (LocalDate.now() < dataEmissao)
            throw Exception("Não é possível registrar a carteirinha com data de entrega menor que data de emissão")

        return copy(status = StatusCarteirinha.VALIDA, dataEntrega = LocalDate.now());
    }

    fun registrarExtravio(): Carteirinha {
        if (status != StatusCarteirinha.VALIDA)
            throw Exception("Só é possível registrar o extravio de uma carteirinha com status: ${StatusCarteirinha.VALIDA}")

        return copy(status = StatusCarteirinha.EXTRAVIADA);
    }

    fun validate() : Boolean {
        if (status == StatusCarteirinha.VALIDA && dataVencimento >= LocalDate.now())
            return true;
        throw Exception("Carteirinha Inválida:\n Status: $status")
    }

    private fun copy(
            numeroCarteirinha: String = this.numeroCarteirinha,
            idUsuario: Int = this.idUsuario,
            dataEmissao: LocalDate = this.dataEmissao,
            dataVencimento: LocalDate = this.dataVencimento,
            dataEntrega: LocalDate? = this.dataEntrega,
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