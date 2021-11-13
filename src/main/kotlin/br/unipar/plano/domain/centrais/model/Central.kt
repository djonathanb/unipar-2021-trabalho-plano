package br.unipar.plano.domain.centrais.model

enum class StatusCentral {
    CRIADA, CREDENCIADA, DESCREDENCIADA
}

class Central(
    val id: IdCentral,
    val nome: String,
    val cnpj: String,
    val endereco: Endereco,
    val status: StatusCentral = StatusCentral.CRIADA
) {

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
