package br.unipar.plano.domain.contratos.model.factories

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.planos.model.Plano
import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.StatusEstadoCivil
import java.time.LocalDate
import java.util.*

fun idContrato (static: Boolean = true) = if (static) {
    CONTRATO_CO_ID
} else {
    IdContrato()
}
fun idPessoa (static: Boolean = true) = if (static) {
    PESSOA_CO_ID
} else {
    IdPessoa()
}

fun planoTeste(idPlano : UUID = UUID.fromString("5a66cdf0-4f59-4a2e-c315-a2b254e9e88a")) = Plano ( id = idPlano )

fun pessoaTest(
    idPessoa: IdPessoa = idPessoa(),
    nome: String = CONTRATO_CO_NOMEPESSOA,
    endereco: String = "RUA SILVEIRA",
    rg: String = "1526262662",
    cpf: String = "10811097935",
    dataDeNascimento: LocalDate = LocalDate.of(1998,11,26),
    nomeMae: String = "IRENI DE SOUZA",
    nomePai: String = "IRINEU DE SOUZA",
    estadoCivil: StatusEstadoCivil = StatusEstadoCivil.AMASIADO
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


fun contrato(
    id: IdContrato = idContrato(),
    dataCadastro: LocalDate = LocalDate.of(2018,11,26),
    dataVencimento: LocalDate = LocalDate.of(2021,11,26),
    plano: Plano = planoTeste(),
    titular : Pessoa = pessoaTest(),
    dataCancelamento: LocalDate = LocalDate.now()
) = Contrato(
    id = id,
    dataContratacao = dataCadastro,
    dataContratoFinal = dataVencimento,
    plano = plano,
    titular = titular,
    dataCancelamento = dataCancelamento
)



