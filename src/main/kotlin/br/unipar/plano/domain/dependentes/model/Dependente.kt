package br.unipar.plano.domain.dependentes.model

import br.unipar.plano.domain.pessoas.model.Pessoa
import javax.persistence.*


@Entity
class Dependente(

    @field:EmbeddedId
    val idDependente: IdDependente,

    @ManyToOne
    val titular: Pessoa

) /*{
    fun with(
        idPessoa: IdDependente = this.idPessoa,
        nome: String = this.nome,
        endereco: String = this.endereco,
        rg: String = this.rg,
        cpf: String = this.cpf,
        estadoCivil: StatusEstadoCivil = this.estadoCivil,
        dataDeNascimento: LocalDate = this.dataDeNascimento,
        nomeMae: String = this.nomeMae,
        nomePai: String = this.nomePai
    ) = copy(
        idPessoa = idPessoa,
        nome = nome,
        endereco = endereco,
        rg = rg,
        cpf = cpf,
        estadoCivil = estadoCivil,
        dataDeNascimento = dataDeNascimento,
        nomeMae = nomeMae,
        nomePai = nomePai
    )

    private fun copy(
        idPessoa: IdDependente = this.idPessoa,
        nome: String = this.nome,
        endereco: String = this.endereco,
        rg: String = this.rg,
        cpf: String = this.cpf,
        estadoCivil: StatusEstadoCivil = this.estadoCivil,
        dataDeNascimento: LocalDate = this.dataDeNascimento,
        nomeMae: String = this.nomeMae,
        nomePai: String = this.nomePai
    ) = Pessoa(
        idPessoa = idPessoa,
        nome = nome,
        endereco = endereco,
        rg = rg,
        cpf = cpf,
        estadoCivil = estadoCivil,
        dataDeNascimento = dataDeNascimento,
        nomeMae = nomeMae,
        nomePai = nomePai
    )
}*/