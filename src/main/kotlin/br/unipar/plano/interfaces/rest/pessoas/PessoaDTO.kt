package br.unipar.plano.interfaces.rest.pessoas

import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.StatusEstadoCivil
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.validation.constraints.NotBlank

/*
data class PessoaSummaryDTO(
    val id: IdContrato,
    val idTitular: UUID,
    val idPlano: UUID,
    val dataContratacao: LocalDate,
    val dataContratoFinal : LocalDate
) {

    companion object {

        fun toDTO(pessoa: Pessoa) = PessoaSummaryDTO(
            id = contrato.idContrato,
            idTitular = contrato.idTitular,
            idPlano = contrato.idPlano,
            dataContratacao = contrato.dataContratacao,
            dataContratoFinal = contrato.dataContratoFinal
        )

    }

}*/

data class PessoaDetailsDTO(
    val id: IdPessoa,
    val pessoaData: PessoaDTO
) {

    companion object {

        fun toDTO(pessoa: Pessoa) = PessoaDetailsDTO(
            id = pessoa.idPessoa,
            pessoaData = PessoaDTO.toDTO(pessoa)
        )

    }

}

data class PessoaDTO(

    @NotBlank(message = "Nome da pessoa Não informado")
    val nome: String,

    @NotBlank(message = "Endereço da pessoa não informado")
    val endereco: String,

    @NotBlank(message = "RG não informado")
    val rg: String,

    @NotBlank(message = "CPF não informado")
    @CPF(message = "O CPF informado é inválido")
    val cpf: String,

    @NotBlank(message = "Estado civil não informado")
    val estadoCivil: StatusEstadoCivil,

    @NotBlank(message = "Data de nascimento não informada")
    val dataDeNascimento: LocalDate,

    val nomeMae: String,

    val nomePai: String,

    //val dependente: List<DependenteDTO>
) {

    fun toModel(id: IdPessoa) = Pessoa(
        idPessoa = id,
        nome = this.nome,
        endereco = this.endereco,
        rg = this.rg,
        cpf = this.cpf,
        estadoCivil = this.estadoCivil,
        dataDeNascimento = this.dataDeNascimento,
        nomeMae = this.nomeMae,
        nomePai = this.nomePai,
        //dependente = this.dependente.map{ toModel(id dependente) }
    )

    companion object {

        fun toDTO(pessoa: Pessoa) = PessoaDTO(
            nome = pessoa.nome,
            endereco = pessoa.endereco,
            rg = pessoa.rg,
            cpf = pessoa.cpf,
            estadoCivil = pessoa.estadoCivil,
            dataDeNascimento = pessoa.dataDeNascimento,
            nomeMae = pessoa.nomeMae,
            nomePai = pessoa.nomePai,
            //dependente = pessoa.dependente
        )
    }

}