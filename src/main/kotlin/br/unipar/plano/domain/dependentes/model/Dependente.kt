package br.unipar.plano.domain.dependentes.model

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.pessoas.model.Pessoa
import javax.persistence.*

enum class TipoDependente {
    CONJUJE, FILHO, PAIS
}

@Entity
class Dependente(

    @field:EmbeddedId
    val idDependente: IdDependente,

    @ManyToOne
    val contrato: Contrato,

    @OneToOne
    val pessoa: Pessoa,

    @Column(name = "tipodependente", nullable = false)
    @Enumerated(EnumType.STRING)
    val tipo: TipoDependente

) {
    fun with(
        idDependente: IdDependente = this.idDependente,
        contrato: Contrato = this.contrato,
        pessoa: Pessoa = this.pessoa,
        tipo: TipoDependente = this.tipo
    ) = copy(
        idDependente = idDependente,
        contrato = contrato,
        pessoa =pessoa,
        tipo = tipo
    )

    private fun copy(
        idDependente: IdDependente = this.idDependente,
        contrato: Contrato = this.contrato,
        pessoa: Pessoa = this.pessoa,
        tipo: TipoDependente = this.tipo
    ) = Dependente(
        idDependente = idDependente,
        contrato = contrato,
        pessoa =pessoa,
        tipo = tipo
    )
}