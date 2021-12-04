package br.unipar.plano.domain.contratos.model.factories

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.planos.model.IdPlano
import br.unipar.plano.domain.contratos.planos.model.Plano
import br.unipar.plano.domain.contratos.planos.model.factories.plano
import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.factories.pessoaTest
import java.time.LocalDate

fun idContrato (static: Boolean = true) = if (static) {
    CONTRATO_CO_ID
} else {
    IdContrato()
}

fun contrato(
    id: IdContrato = idContrato(),
    dataCadastro: LocalDate = CONTRATO_CO_DATACADASTRO,
    dataVencimento: LocalDate = CONTRATO_CO_VENCIMENTO,
    plano: Plano = plano(),
    titular : Pessoa = pessoaTest(),
    dataCancelamento: LocalDate = CONTRATO_CO_DATACANCELAMENTO

) = Contrato(
    id = id,
    dataContratacao = dataCadastro,
    dataContratoFinal = dataVencimento,
    plano = plano,
    titular = titular,
    dataCancelamento = dataCancelamento,
    dependentes = null
)



