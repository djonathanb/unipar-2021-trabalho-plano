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

fun idPlano (static: Boolean = true) = if (static) {
    PLANO_CO_ID
} else {
    IdPlano()
}




fun contrato(
    id: IdContrato = idContrato(),
    dataCadastro: LocalDate = LocalDate.of(2018,11,26),
    dataVencimento: LocalDate = LocalDate.of(2021,11,26),
    plano: Plano = plano(),
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



