package br.unipar.plano.domain.contratos.model

import br.unipar.plano.domain.contratos.planos.model.Plano
import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.pessoas.model.Pessoa
import java.time.LocalDate
import javax.persistence.*

enum class StatusContrato {
    ATIVO, CANCELADO
}

@Entity
class Contrato(

    @field:EmbeddedId
    val id: IdContrato,

    @Column(nullable = false)
    var dataContratacao: LocalDate,

    @Column(nullable = false)
    val dataContratoFinal: LocalDate,

    @Column(nullable = false)
    val dataCancelamento: LocalDate?,

    @ManyToOne
    val plano: Plano,

    @OneToOne
    val titular: Pessoa,

    @Enumerated(EnumType.STRING)
    val status: StatusContrato = StatusContrato.ATIVO,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "contrato")
    val dependentes: List<Dependente>?

) {
    fun with(
        id: IdContrato = this.id,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        plano: Plano = this.plano,
        dependentes: List<Dependente>? = this.dependentes
    ) = copy(
        idContrato = id,
        dataContratoFinal = dataContratoFinal,
        plano = plano,
        dependentes = dependentes
    )

    private fun copy(
        idContrato: IdContrato = this.id,
        dataContratacao: LocalDate = this.dataContratacao,
        dataContratoFinal: LocalDate = this.dataContratoFinal,
        plano: Plano = this.plano,
        Titular: Pessoa = this.titular,
        status: StatusContrato = this.status,
        dataCancelamento: LocalDate? = this.dataCancelamento,
        dependentes: List<Dependente>? = this.dependentes
    ) = Contrato(
        id = idContrato,
        dataContratacao = dataContratacao,
        dataContratoFinal = dataContratoFinal,
        plano = plano,
        titular = Titular,
        status = status,
        dataCancelamento = dataCancelamento,
        dependentes = dependentes
    )

    fun cancela(): Contrato {
        if (status != StatusContrato.ATIVO) {
            throw IllegalStateException("Não é possível cancelar um Contrato com status $status")
        }
        return copy(status = StatusContrato.CANCELADO, dataCancelamento = LocalDate.now())
    }
}