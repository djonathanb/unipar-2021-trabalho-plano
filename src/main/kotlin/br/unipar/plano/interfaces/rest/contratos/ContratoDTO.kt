package br.unipar.plano.interfaces.rest.contratos

import br.unipar.plano.domain.contratos.model.Contrato
import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.contratos.planos.model.Plano
import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.interfaces.rest.dependentes.DependenteDTO
import java.time.LocalDate
import javax.validation.constraints.NotNull

data class ContratoSummaryDTO(
    val id: IdContrato,
    val idTitular: Pessoa,
    val idPlano: Plano,
    val dataContratoFinal : LocalDate,
    val dependente: List<Dependente>?
) {

    companion object {

        fun toDTO(contrato: Contrato) = ContratoSummaryDTO(
            id = contrato.id,
            idTitular = contrato.titular,
            idPlano = contrato.plano,
            dataContratoFinal = contrato.dataContratoFinal,
            dependente = contrato.dependentes
        )

    }

}

data class ContratoDetailsDTO(
    val id: IdContrato,
    val status: StatusContrato,
    val contratoData: ContratoDTO
) {

    companion object {

        fun toDTO(contrato: Contrato) = ContratoDetailsDTO(
            id = contrato.id,
            status = contrato.status,
            contratoData = ContratoDTO.toDTO(contrato)
        )

    }

}

data class ContratoDTO(
    @NotNull(message = "Data de contratação não informada")
    val dataContratacao: LocalDate,

    @NotNull(message = "Data de termino de contrato não informada")
    val dataContratoFinal: LocalDate,

    @NotNull(message = "Plano não informado") //Arrumar mensagem todo
    val idPlano: Plano,

    @NotNull(message = "Titular não informado") //Arrumar mensagem todo
    val idTitular: Pessoa,

    val dataCancelamento: LocalDate?,

    val dependentes: List<DependenteDTO>?

) {

    fun toModel(id: IdContrato) = Contrato(
        id = id,
        dataContratacao = this.dataContratacao,
        dataContratoFinal = this.dataContratoFinal,
        plano = this.idPlano,
        titular = this.idTitular,
        dataCancelamento = this.dataCancelamento,
        dependentes = this.dependentes?.map{ it.toModel(it.idDependente, pessoa = idTitular)}
    )

    companion object {

        fun toDTO(contrato: Contrato) = ContratoDTO(
            dataContratacao = contrato.dataContratacao,
            dataContratoFinal = contrato.dataContratoFinal,
            idPlano = contrato.plano,
            idTitular = contrato.titular,
            dataCancelamento = contrato.dataCancelamento,
            dependentes = contrato.dependentes?.map{DependenteDTO.toDTO(it)}
        )
    }

}