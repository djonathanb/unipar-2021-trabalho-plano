package br.unipar.plano.domain.pessoas.model.factories

import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.StatusEstadoCivil
import java.time.LocalDate

fun idPessoa (static: Boolean = true) = if (static) {
    PESSOA_CO_ID
} else {
    IdPessoa()
}

fun pessoaTest(
    idPessoa:  IdPessoa = idPessoa(),
    nome: String = PESSOA_CO_NOMEPESSOA,
    endereco: String = PESSOA_CO_ENDERECO,
    rg: String = PESSOA_CO_RG,
    cpf: String = PESSOA_CO_CPF,
    dataDeNascimento: LocalDate = PESSOA_CO_DT_NASCIMENTO,
    nomeMae: String = PESSOA_CO_NOME_MAE,
    nomePai: String = PESSOA_CO_NOME_PAI,
    estadoCivil: StatusEstadoCivil = PESSOA_CO_ESTADO_CIVIL
) = Pessoa (
    idPessoa = idPessoa,
    nome = nome,
    endereco = endereco,
    rg = rg,
    cpf = cpf,
    dataDeNascimento = dataDeNascimento,
    nomeMae = nomeMae,
    nomePai = nomePai,
    estadoCivil = estadoCivil
)