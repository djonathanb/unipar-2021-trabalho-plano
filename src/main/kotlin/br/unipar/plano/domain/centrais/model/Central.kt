package br.unipar.plano.domain.centrais.model

import javax.persistence.*

enum class StatusCentral {
    CRIADA, CREDENCIADA, DESCREDENCIADA
}

@Entity
class Central(

    @field:EmbeddedId
    val id: IdCentral,

    @Column(nullable = false)
    val nome: String,

    @Column(nullable = false)
    val cnpj: String,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    val endereco: Endereco,

    @Enumerated(EnumType.STRING)
    val status: StatusCentral = StatusCentral.CRIADA

) {

    fun credencia(): Central {
        if (status == StatusCentral.CREDENCIADA) {
            throw Exception("Não é possível credenciar uma Central com status $status")
        }
        return copy(status = StatusCentral.CREDENCIADA)
    }

    fun descredencia(): Central {
        if (status != StatusCentral.CREDENCIADA) {
            throw Exception("Não é possível descredenciar uma Central com status $status")
        }
        return copy(status = StatusCentral.DESCREDENCIADA)
    }

    fun with(
        id: IdCentral = this.id,
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
        id: IdCentral = this.id,
        nome: String = this.nome,
        cnpj: String = this.cnpj,
        endereco: Endereco = this.endereco,
        status: StatusCentral = this.status
    ) = Central(
        id = id,
        nome = nome,
        cnpj = cnpj,
        endereco = endereco,
        status = status
    )

}
