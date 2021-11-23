package br.unipar.plano.domain.procedimento.model

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

enum class StatusProcedimento {
    CANCELADO, REALIZADO, PENDENTE
}

@Entity
class Central(

        @field:EmbeddedId
        val id: IdProcedimento,

        @Column(nullable = false)
        val dataEmissao: LocalDate,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        val contrato: Contrato,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        val prestador: Prestador,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        val carteirinha: Carteirinha,

        @Column(nullable = false)
        val valor: BigDecimal,

        @Enumerated(EnumType.STRING)
        val status: StatusProcedimento = StatusProcedimento.CANCELADO,

        @Column(nullable = false)
        val dataCancelamento: LocalDate,

        @Column(nullable = false)
        val dataProcedimento: LocalDate,

        @Enumerated(EnumType.STRING)
        val servico: Servico,

        @Enumerated(EnumType.STRING)
        val especialidade: Especialidade

) {

    fun with(
            id: IdProcedimento = this.id,
            nome: String = this.nome,
            cnpj: String = this.cnpj,
            endereco: Endereco = this.endereco
    ) = copy(
            id = id,
            nome = nome,
            cnpj = cnpj,
            endereco = endereco
    )

    private fun copy(
            id: IdProcedimento = this.id,
            nome: String = this.nome,
            cnpj: String = this.cnpj,
            endereco: Endereco = this.endereco,
            status: StatusProcedimento = this.status
    ) = Central(
            id = id,
            nome = nome,
            cnpj = cnpj,
            endereco = endereco,
            status = status
    )
}
