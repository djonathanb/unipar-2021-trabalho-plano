package br.unipar.plano.domain.pessoas.model


import br.unipar.plano.domain.dependentes.model.Dependente
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.persistence.*

enum class StatusEstadoCivil {
    CASADO, SOLTEIRO, DIVORCIADO, VIUVO, AMASIADO
}

@Entity
class Pessoa(

    @field:EmbeddedId
    val idPessoa: IdPessoa,

    @Column(nullable = false)
    val endereco: String,

    @Column(nullable = false)
    val nome: String,

    @Column(nullable = false)
    val rg: String,

    @Column(nullable = false)
    @CPF
    val cpf: String,

    @Enumerated(EnumType.STRING)
    val estadoCivil: StatusEstadoCivil,

    @Column(nullable = false)
    val dataDeNascimento: LocalDate,

    @Column()
    val nomeMae: String,

    @Column()
    val nomePai: String,

   // @OneToMany(cascade = [CascadeType.ALL], mappedBy = "titular")
   // val dependentes: List<Dependente>?

) {
    fun with(
        idPessoa: IdPessoa = this.idPessoa,
        nome: String = this.nome,
        endereco: String = this.endereco,
        rg: String = this.rg,
        cpf: String = this.cpf,
        estadoCivil: StatusEstadoCivil = this.estadoCivil,
        dataDeNascimento: LocalDate = this.dataDeNascimento,
        nomeMae: String = this.nomeMae,
        nomePai: String = this.nomePai,
       // dependentes: List<Dependente>? = this.dependentes
    ) = copy(
        idPessoa = idPessoa,
        nome = nome,
        endereco = endereco,
        rg = rg,
        cpf = cpf,
        estadoCivil = estadoCivil,
        dataDeNascimento = dataDeNascimento,
        nomeMae = nomeMae,
        nomePai = nomePai,
     //   dependentes = this.dependentes
    )

    private fun copy(
        idPessoa: IdPessoa = this.idPessoa,
        nome: String = this.nome,
        endereco: String = this.endereco,
        rg: String = this.rg,
        cpf: String = this.cpf,
        estadoCivil: StatusEstadoCivil = this.estadoCivil,
        dataDeNascimento: LocalDate = this.dataDeNascimento,
        nomeMae: String = this.nomeMae,
        nomePai: String = this.nomePai,
       // dependentes: List<Dependente>? = this.dependentes
    ) = Pessoa(
        idPessoa = idPessoa,
        nome = nome,
        endereco = endereco,
        rg = rg,
        cpf = cpf,
        estadoCivil = estadoCivil,
        dataDeNascimento = dataDeNascimento,
        nomeMae = nomeMae,
        nomePai = nomePai ,
        //dependentes = dependentes
    )
}