package br.unipar.plano.interfaces.rest.dependentes.factories

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.factories.contrato
import br.unipar.plano.domain.dependentes.model.IdDependente
import br.unipar.plano.domain.dependentes.model.TipoDependente
import br.unipar.plano.domain.dependentes.model.factories.DEPENDENTE_CO_TIPO
import br.unipar.plano.domain.dependentes.model.factories.idDependente
import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.factories.pessoaTest
import br.unipar.plano.interfaces.rest.dependentes.DependenteDTO
import br.unipar.plano.interfaces.rest.dependentes.DependenteDetailsDTO


fun dependenteDetailsDTO(
    id : IdDependente = idDependente(),
    dependeteData : DependenteDTO = dependenteDTO()
) = DependenteDetailsDTO(
    id = id,
    dependenteData = dependeteData
)

fun dependenteDTO(
    idDependente: IdDependente = idDependente(),
    pessoa : Pessoa = pessoaTest(),
    tipo : TipoDependente = DEPENDENTE_CO_TIPO,
    contrato : Contrato = contrato()
) = DependenteDTO(
    idDependente = idDependente,
    pessoa = pessoa,
    tipo = tipo,
    contrato = contrato
)