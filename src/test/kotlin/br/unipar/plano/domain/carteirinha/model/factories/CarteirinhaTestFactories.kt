package br.unipar.plano.domain.carteirinha.model.factories

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import java.time.LocalDate

fun carteirinha(
        numeroCarteirinha: String = CARTEIRINHA_NUMERO_CARTEIRINHA_VALIDO,
        idUsuario: Int = CARTEIRINHA_ID_USUARIO,
        dataEmissao: LocalDate = LocalDate.now(),
        dataEntrega: LocalDate? = null,
        dataVencimento: LocalDate = LocalDate.now().plusYears(3)
) = Carteirinha(
        numeroCarteirinha = numeroCarteirinha,
        idUsuario = idUsuario,
        dataEmissao = dataEmissao,
        dataEntrega = dataEntrega,
        dataVencimento = dataVencimento
)