package br.unipar.plano.domain.dependentes.model.factories

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.factories.contrato
import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.dependentes.model.IdDependente
import br.unipar.plano.domain.dependentes.model.TipoDependente
import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.factories.pessoaTest
import javax.persistence.*


fun idDependente (static: Boolean = true) = if (static) {
    DEPENDENTE_CO_ID
} else {
    IdDependente()
}

fun dependente(
    id: IdDependente = idDependente(),
    contrato: Contrato = contrato(),
    pessoa: Pessoa = pessoaTest(),
    tipo: TipoDependente = DEPENDENTE_CO_TIPO
) = Dependente(
    idDependente = id,
    contrato = contrato,
    pessoa = pessoa,
    tipo = tipo

)