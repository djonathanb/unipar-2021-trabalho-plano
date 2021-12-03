package br.unipar.plano.interfaces.rest.dependentes

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.dependentes.model.IdDependente
import br.unipar.plano.domain.dependentes.model.TipoDependente
import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.interfaces.rest.pessoas.PessoaDTO
import br.unipar.plano.interfaces.rest.pessoas.PessoaSummaryDTO
import javax.validation.constraints.NotNull


data class DependenteDTO(
    @NotNull(message = "finalizar mensagem") // todo
    val idDependente: IdDependente,

    @NotNull(message = "A pessoa deve ser informada !")
    val pessoa: PessoaSummaryDTO,

    @NotNull(message = "O Tipo de Dependente deve ser informado !")
    val tipo: TipoDependente

) {

    fun toModel(id: IdDependente = this.idDependente, contrato: Contrato, pessoa: Pessoa) = Dependente(
        idDependente = id,
        contrato = contrato,
        pessoa = pessoa,
        tipo = this.tipo
    )


    companion object {

        fun toDTO(dependente: Dependente) = DependenteDTO(
            idDependente = dependente.idDependente,
            pessoa = PessoaSummaryDTO.toDTO(dependente.pessoa),
            tipo = dependente.tipo
        )
    }

}