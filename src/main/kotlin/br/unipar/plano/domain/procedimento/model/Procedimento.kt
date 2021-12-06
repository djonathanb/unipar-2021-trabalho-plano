package br.unipar.plano.domain.procedimento.model

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.centrais.model.Central
import br.unipar.plano.domain.centrais.model.StatusCentral
import br.unipar.plano.domain.contrato.model.Contrato
import br.unipar.plano.domain.prestador.model.Prestador
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
class Procedimento(

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
        val status: StatusProcedimento = StatusProcedimento.PENDENTE,

        @Column(nullable = false)
        val dataCancelamento: LocalDate,

        @Column(nullable = false)
        val dataProcedimento: LocalDate,

        @Enumerated(EnumType.STRING)
        val servico: Servico,

        @Enumerated(EnumType.STRING)
        val especialidade: Especialidade

) {

    fun cancela(): Procedimento {
        if (status != StatusProcedimento.PENDENTE) {
            throw IllegalStateException("Não é possível cancelar um procedimento com status $status")
        }
        return copy(status = StatusProcedimento.CANCELADO, dataCancelamento = LocalDate.now())
    }

    fun with(
            id: IdProcedimento = this.id,
            dataEmissao: LocalDate = this.dataEmissao,
            contrato: Contrato = this.contrato,
            prestador: Prestador = this.prestador,
            carteirinha: Carteirinha = this.carteirinha,
            valor: BigDecimal = this.valor,
            status: StatusProcedimento = this.status,
            dataCancelamento: LocalDate = this.dataCancelamento,
            dataProcedimento: LocalDate = this.dataProcedimento,
            servico: Servico = this.servico,
            especialidade: Especialidade = this.especialidade
    ) = copy(
            id = id,
            dataEmissao = dataEmissao,
            contrato = contrato,
            prestador = prestador,
            carteirinha = carteirinha,
            valor = valor,
            status = status,
            dataCancelamento = dataCancelamento,
            dataProcedimento = dataProcedimento,
            servico = servico,
            especialidade = especialidade
    )

    private fun copy(
            id: IdProcedimento = this.id,
            dataEmissao: LocalDate = this.dataEmissao,
            contrato: Contrato = this.contrato,
            prestador: Prestador = this.prestador,
            carteirinha: Carteirinha = this.carteirinha,
            valor: BigDecimal = this.valor,
            status: StatusProcedimento = this.status,
            dataCancelamento: LocalDate = this.dataCancelamento,
            dataProcedimento: LocalDate = this.dataProcedimento,
            servico: Servico = this.servico,
            especialidade: Especialidade = this.especialidade
    ) = Procedimento(
            id = id,
            dataEmissao = dataEmissao,
            contrato = contrato,
            prestador = prestador,
            carteirinha = carteirinha,
            valor = valor,
            status = status,
            dataCancelamento = dataCancelamento,
            dataProcedimento = dataProcedimento,
            servico = servico,
            especialidade = especialidade
    )
}
