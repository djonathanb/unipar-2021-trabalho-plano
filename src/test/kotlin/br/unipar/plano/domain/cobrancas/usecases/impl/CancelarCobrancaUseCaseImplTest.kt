package br.unipar.plano.domain.cobrancas.usecases.impl

import br.unipar.plano.domain.cobrancas.commands.CancelarCobrancaCommand
import br.unipar.plano.domain.cobrancas.gateway.CobrancaGateway
import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.factories.cobranca
import br.unipar.plano.domain.cobrancas.model.factories.contrato
import br.unipar.plano.domain.cobrancas.model.toCobrancaView
import br.unipar.plano.domain.cobrancas.service.CobrancaQueryService
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import java.time.LocalDate

class CancelarCobrancaUseCaseImplTest {
    private var gateway = mock<CobrancaGateway>()
    private var queryService = mock<CobrancaQueryService>()
    private var cancelarCobrancaUseCaseImpl = CancelarCobrancaUseCaseImpl(queryService, gateway)


    @BeforeEach
    fun setup() {
        Mockito.`when`(gateway.salvar(any())).doAnswer { it -> it.arguments[0] as Cobranca? }
    }

    @Test
    fun `deve cancelar a cobranca`() {
        val cobranca = cobranca(dataCancelamento = null)
        val contrato = contrato()
        Mockito.`when`(queryService.buscaPorId(any(), any())).thenReturn(cobranca.toCobrancaView())
        val cobrancaCancelada = cancelarCobrancaUseCaseImpl.executa(CancelarCobrancaCommand(contrato.id, cobranca.id))
        assertEquals(cobrancaCancelada.status, StatusCobranca.CANCELADO)
        assertNotNull(cobrancaCancelada.dataCancelamento)
        assertEquals(cobrancaCancelada.dataCancelamento, LocalDate.now())
        assertNotEquals(cobrancaCancelada, cobranca)
    }

}