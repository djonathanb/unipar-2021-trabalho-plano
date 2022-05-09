package br.unipar.plano.domain.cobrancas.service.impl

import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.model.IdContrato
import br.unipar.plano.infra.cobrancas.repository.CobrancaQueryRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import java.util.*

internal class CobrancaQueryServiceImplTest {
    private var repository = mock<CobrancaQueryRepository>()
    private var cobrancaQueryServiceImpl = CobrancaQueryServiceImpl(repository)

    @Test
    fun `deve retornar uma excecao de Cobranca nao encontrada`() {
        Mockito.`when`(repository.findById(any(), any())).thenReturn(Optional.empty())
        org.junit.jupiter.api.assertThrows<CobrancaNotFoundException> {
            cobrancaQueryServiceImpl.buscaPorId(IdContrato(), IdCobranca())
        }
    }
}