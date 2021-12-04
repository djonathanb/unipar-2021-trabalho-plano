package br.unipar.plano.interfaces.rest.contratos.factories

import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.model.StatusContrato
import br.unipar.plano.domain.contratos.model.factories.*
import br.unipar.plano.domain.contratos.planos.model.Plano
import br.unipar.plano.domain.contratos.planos.model.factories.plano
import br.unipar.plano.domain.dependentes.model.Dependente
import br.unipar.plano.domain.pessoas.model.Pessoa
import br.unipar.plano.domain.pessoas.model.factories.pessoaTest
import br.unipar.plano.interfaces.rest.contratos.ContratoDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoDetailsDTO
import br.unipar.plano.interfaces.rest.contratos.ContratoSummaryDTO
import java.time.LocalDate

fun contratoDTO(
    dataCadastro: LocalDate = CONTRATO_CO_DATACADASTRO,
    dataVencimento: LocalDate = CONTRATO_CO_VENCIMENTO,
    plano: Plano = plano(),
    titular : Pessoa = pessoaTest(),
    dataCancelamento: LocalDate = CONTRATO_CO_DATACANCELAMENTO
) = ContratoDTO (
    dataContratacao =  dataCadastro,
    dataContratoFinal = dataVencimento,
    idPlano = plano,
    idTitular = titular,
    dataCancelamento = dataCancelamento,
    dependentes = null
)

fun contratoDetailsDTO(
    id: IdContrato = idContrato(static = true),
    status: StatusContrato = CONTRATO_CO_STATUS,
    contratoData: ContratoDTO = contratoDTO()
) = ContratoDetailsDTO(
    id = id,
    status = status,
    contratoData = contratoData
)

fun contratoSummaryDTO(
    staticId : Boolean = true,
    id: IdContrato = idContrato(static = staticId),
    idTitular: Pessoa = pessoaTest(),
    idPlano: Plano = plano(),
    dataContratacao: LocalDate = CONTRATO_CO_DATACADASTRO,
    dataContratoFinal : LocalDate = CONTRATO_CO_VENCIMENTO,
    dependente: List<Dependente>? = null
) = ContratoSummaryDTO(
    id = id,
    idTitular = idTitular,
    idPlano = idPlano,
    dataContratacao = dataContratacao,
    dataContratoFinal = dataContratoFinal,
    dependente = dependente

)