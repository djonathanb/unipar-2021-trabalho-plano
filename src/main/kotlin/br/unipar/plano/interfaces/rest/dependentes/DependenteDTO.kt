package br.unipar.plano.interfaces.rest.dependentes

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.dependentes.model.IdDependente
import br.unipar.plano.domain.dependentes.model.TipoDependente
import br.unipar.plano.domain.pessoas.model.Pessoa

import javax.validation.constraints.NotNull


data class DependenteDetailsDTO(
    val id: IdDependente,
    val dependenteData : DependenteDTO
) {

    companion object {

        fun toDTO(dependente: Dependente) = DependenteDetailsDTO(
            id = dependente.idDependente,
            dependenteData = DependenteDTO.toDTO(dependente)
        )

    }

}

data class DependenteDTO(

    @NotNull(message = "ID") // todo
    val idDependente: IdDependente,

    @NotNull(message = "A pessoa deve ser informada !")
    val pessoa: Pessoa,

    @NotNull(message = "O Tipo de Dependente deve ser informado !")
    val tipo: TipoDependente,

    @NotNull(message = "O contrato deve ser informado !")
    val contrato : Contrato
) {

    fun toModel(id: IdDependente = this.idDependente, pessoa: Pessoa) = Dependente(
        idDependente = id,
        pessoa = pessoa,
        tipo = this.tipo,
        contrato = this.contrato
    )


    companion object {

        fun toDTO(dependente: Dependente) = DependenteDTO(
            idDependente = dependente.idDependente,
            pessoa = dependente.pessoa,
            tipo = dependente.tipo,
            contrato = dependente.contrato
        )
    }

}