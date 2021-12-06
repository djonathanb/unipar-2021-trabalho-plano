package br.unipar.plano.domain.cobrancas.service.impl

import br.unipar.plano.domain.cobrancas.model.IdCobranca
import br.unipar.plano.domain.cobrancas.repository.CobrancaRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import java.util.*

internal class CobrancaQueryServiceImplTest {
    private var repository = mock<CobrancaRepository>()
    private var cobrancaQueryServiceImpl = CobrancaQueryServiceImpl(repository)

    @Test
    fun `deve retornar uma excecao de Cobranca nao encontrada`() {
        Mockito.`when`(repository.findById(any())).thenReturn(Optional.empty())
        org.junit.jupiter.api.assertThrows<CobrancaNotFoundException> {
            cobrancaQueryServiceImpl.buscaPorId(IdCobranca())
        }
    }
}