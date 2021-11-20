package br.unipar.plano.domain.cobrancas.usecases.impl

import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.factories.cobranca
import br.unipar.plano.domain.cobrancas.repository.CobrancaRepository
import br.unipar.plano.domain.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.cobrancas.service.impl.CobrancaNotFoundException
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import java.time.LocalDate

class CancelarCobrancaUseCaseImplTest {
    private var repository = mock<CobrancaRepository>()
    private var queryService = mock<CobrancaQueryService>()
    private var cancelarCobrancaUseCaseImpl = CancelarCobrancaUseCaseImpl(queryService, repository)


    @BeforeEach
    fun setup() {
        Mockito.`when`(repository.save(any())).doAnswer { it -> it.arguments[0] as Cobranca? }
    }

    @Test
    fun `deve cancelar a cobranca`() {

        var cobranca = cobranca(dataCancelamento = null, valorTotal = null);
        Mockito.`when`(queryService.buscaPorId(any())).thenReturn(cobranca)
        cobranca = cancelarCobrancaUseCaseImpl.executa(cobranca.id)
        assertEquals(cobranca.status, StatusCobranca.CANCELADO)
        assertNotNull(cobranca.dataCancelamento)
        assertEquals(cobranca.dataCancelamento, LocalDate.now())
    }

}