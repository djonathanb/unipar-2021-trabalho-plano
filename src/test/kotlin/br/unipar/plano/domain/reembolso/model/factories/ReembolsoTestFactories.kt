package br.unipar.plano.domain.reembolso.model.factories

import br.unipar.plano.domain.reembolso.model.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

fun idReembolso(static: Boolean = true) = if (static) {
    REEMBOLSO_ID
} else {
    IdReembolso()
}

fun idRejeicao(static: Boolean = true) = if (static) {
        REJEICAO_ID
} else {
        IdRejeicao()
}

fun idUsuario(static: Boolean = true) = if (static) {
    USUARIO_ID
} else {
    IdUsuario()
}

fun idCarteirinha(static: Boolean = true) = if (static) {
    CARTEIRINHA_ID
} else {
    IdCarteirinha()
}

fun idPlano(static: Boolean = true) = if (static) {
    PLANO_ID
} else {
    UUID.randomUUID()
}

fun rejeicao(
        idRejeicao: IdRejeicao = idRejeicao(),
        dataRejeicao: LocalDate = REJEICAO_DATA,
        motivo: String = REJEICAO_MOTIVO,
        agente: Int = REJEICAO_AGENTE,
        reembolso: Reembolso = reembolso(idReembolso = idReembolso())
) = RejeicaoReembolso(
        id = idRejeicao,
        dataRejeicao = dataRejeicao,
        motivo = motivo,
        agente = agente,
        reembolso = reembolso
)

fun reembolso(
        idReembolso: IdReembolso = idReembolso(),
        estadoSolicitacao: Estado = REEMBOLSO_ESTADO,
        statusReembolso: StatusReembolso = REEMBOLSO_STATUS,
        valor: BigDecimal = REEMBOLSO_VALOR,
        dataSolicitacao: LocalDate = REEMBOLSO_DATA_SOLICITACAO,
        dataAnalize: LocalDate = REEMBOLSO_DATA_ANALIZE,
        usuario: Usuario = usuario(idUsuario = idUsuario()),
        rejeicaoReembolso: RejeicaoReembolso = rejeicao(idRejeicao = idRejeicao())
) = Reembolso(
        id = idReembolso,
        estadoSolicitacao = estadoSolicitacao,
        statusReembolso = statusReembolso,
        valor = valor,
        dataSolicitacao = dataSolicitacao,
        dataAnalize = dataAnalize,
        usuario = usuario,
        rejeicaoReembolso = rejeicaoReembolso
)

fun usuario(
        idUsuario: IdUsuario = idUsuario(),
        nome: String = USUARIO_NOME,
        cpf: String = USUARIO_CPF
) = Usuario (
        id = idUsuario,
        nome = nome,
        cpf = cpf
)

fun carteirinha(
        id: IdCarteirinha = IdCarteirinha(),
        numero: String = CARTEIRINHA_NUMERO,
        statusCarteirinha: StatusCarteirinha = CARTEIRNHA_STATUS,
        dataValidade: LocalDate = CARTEIRNHA_DATA_VALIDADE,
        usuario: Usuario = usuario(idUsuario = idUsuario())
) = Carteirinha (
        id = id,
        numero = numero,
        statusCarteirinha = statusCarteirinha,
        dataValidade = dataValidade,
        usuario = usuario
)

fun plano(
        id: UUID = idPlano(),
        tipoAbrangencia: TipoAbrangencia = PLANO_TIPO_ABRANGENCIA,
        areaAbrangencia: Estado = PLANO_ATEA_ABRANGENCIA,
        usuario: Usuario = usuario(idUsuario = idUsuario())
) = Plano (
        id = id,
        tipoAbrangencia = tipoAbrangencia,
        areaAbrangencia = areaAbrangencia,
        usuario = usuario
)