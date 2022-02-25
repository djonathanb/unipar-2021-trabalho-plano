package br.unipar.plano.interfaces.rest.cobrancas.factories

import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.factories.*
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import br.unipar.plano.interfaces.rest.cobrancas.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*


fun contratoDTO(
    id: UUID = UUID.randomUUID(),
    dependentes: List<UsuarioDTO> = listOf(
        usuarioDTO(),
        usuarioDTO(USUARIO_ID_2, planoDTO(PLANO_ID_2, VALOR_PLANO_600REAIS))
    )
) = ContratoDTO(id = id, dependentes = dependentes)

fun cobrancaSummaryDTO(
    staticId: Boolean = true,
    idCobranca: IdCobranca = idCobranca(staticId),
    valorContrato: BigDecimal = VALOR_CONTRATO,
    dataEmissao: LocalDate = DATA_EMISSAO_COBRANCA,
    dataVencimento: LocalDate = DATA_VENCIMENTO_COBRANCA,
    valorTotal: BigDecimal? = null,
    contrato: ContratoDTO = contratoDTO()
) = CobrancaSummaryDTO(
    id = idCobranca,
    valorContrato = valorContrato,
    dataEmissao = dataEmissao,
    dataVencimento = dataVencimento,
    valorTotal = valorTotal,
    contrato = contrato
)

fun cobrancaDTO(
    valorContrato: BigDecimal = VALOR_CONTRATO,
    valorAdicionalConsulta: BigDecimal = VALOR_ADICIONAL_CONSULTA,
    valorAdicionalCirurgia: BigDecimal = VALOR_ADICIONAL_CIRURGIA,
    valorAdicionalIdade: BigDecimal = VALOR_ADICIONAL_IDADE,
    dataEmissao: LocalDate = DATA_EMISSAO_COBRANCA,
    dataCancelamento: LocalDate? = null,
    dataVencimento: LocalDate = DATA_VENCIMENTO_COBRANCA,
    valorTotal: BigDecimal? = null,
    contrato: ContratoDTO = contratoDTO()
) = CobrancaDTO(
    valorContrato,
    valorAdicionalConsulta,
    valorAdicionalCirurgia,
    valorAdicionalIdade,
    dataEmissao,
    dataCancelamento,
    dataVencimento,
    valorTotal,
    contrato
)

fun cobrancaDetailsDTO(
    staticId: Boolean = true,
    idCobranca: IdCobranca = idCobranca(staticId),
    status: StatusCobranca = STATUS_CoBRANCA_ABERTO,
    cobrancaData: CobrancaDTO = cobrancaDTO()
) = CobrancaDetailsDTO(
    id = idCobranca,
    status = status,
    cobrancaData = cobrancaData
)

fun procedimentoDTO(id: UUID = PROCEDIMENTO_ID_1) = ProcedimentoDTO(id)

fun cirurgiaDTO(id: UUID = CIRURGIA_ID_1) = CirurgiaDTO(id)

fun planoDTO(id: UUID = PLANO_ID_1, valorBase: BigDecimal = VALOR_PLANO_500REAIS) = PlanoDTO(id, valorBase)
fun usuarioDTO(
    id: UUID = USUARIO_ID_1,
    planoDTO: PlanoDTO = planoDTO(),
    dataNascimento: LocalDate = DATA_NASCIMENTO_DEPENDENTE_1
) = UsuarioDTO(id, planoDTO, dataNascimento)

fun registrarCobrancaDTO(
    dataEmissao: LocalDate = DATA_EMISSAO_COBRANCA,
    procedimentos: List<ProcedimentoDTO> = listOf(procedimentoDTO(), procedimentoDTO(PROCEDIMENTO_ID_2)),
    cirurgias: List<CirurgiaDTO> = listOf(cirurgiaDTO(), cirurgiaDTO(CIRURGIA_ID_2)),
) =
    RegistrarCobrancaDTO(dataEmissao = dataEmissao, procedimentos, cirurgias)