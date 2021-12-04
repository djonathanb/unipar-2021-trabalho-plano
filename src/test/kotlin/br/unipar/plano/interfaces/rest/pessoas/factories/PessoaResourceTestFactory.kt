package br.unipar.plano.interfaces.rest.pessoas.factories

import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.model.StatusEstadoCivil
import br.unipar.plano.domain.pessoas.model.factories.*
import br.unipar.plano.interfaces.rest.pessoas.PessoaDTO
import br.unipar.plano.interfaces.rest.pessoas.PessoaDetailsDTO
import java.time.LocalDate

fun pessoaDTO(
    nome: String = PESSOA_CO_NOMEPESSOA,
    endereco: String = PESSOA_CO_ENDERECO,
    rg: String = PESSOA_CO_RG,
    cpf: String = PESSOA_CO_CPF,
    dataDeNascimento: LocalDate = PESSOA_CO_DT_NASCIMENTO,
    nomeMae: String = PESSOA_CO_NOME_MAE,
    nomePai: String = PESSOA_CO_NOME_PAI,
    estadoCivil: StatusEstadoCivil = PESSOA_CO_ESTADO_CIVIL
) = PessoaDTO(
    nome = nome,
    endereco = endereco,
    rg = rg,
    cpf = cpf,
    estadoCivil = estadoCivil,
    dataDeNascimento = dataDeNascimento,
    nomeMae = nomeMae,
    nomePai = nomePai,
)

fun pessoaDetailsDto(
    staticId: Boolean = true,
    id: IdPessoa = idPessoa(staticId),
    pessoaData : PessoaDTO = pessoaDTO()
) = PessoaDetailsDTO(
    id = id,
    pessoaData = pessoaData
)