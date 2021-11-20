package br.unipar.plano.application.service.impl

import br.unipar.plano.application.exception.NegocioException
import br.unipar.plano.domain.model.*
import br.unipar.plano.domain.valueobjects.StatusCobranca
import br.unipar.plano.repository.CobrancaRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.util.*

class CobrancaServiceImplTest() {
    private lateinit var service: CobrancaServiceImpl

    @Mock
    lateinit var repository: CobrancaRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(repository.save(any())).doAnswer { it -> it.arguments[0] as Cobranca? }
        service = CobrancaServiceImpl(repository, BigDecimal.valueOf(1000), BigDecimal.valueOf(10), 10)
    }

    @Test
    fun `deve registrar uma cobranca`() {
        val contrato = getContratoMockado()
        val cobranca = service.registrarCobranca(contrato, LocalDate.of(2021, 10, 11))
        assertNotNull(cobranca)
        assertEquals(
            cobranca.valorTotal,
            BigDecimal.valueOf(1100) // valor dos planos
                .add(BigDecimal.valueOf(2000)) // valor adicional das cirurgias
                .add(BigDecimal.valueOf(20)) // valor Adicional dos procedimentos
                .add(BigDecimal.valueOf(23 + 21))// valor adicional das idades
                .setScale(2, RoundingMode.HALF_UP)
        )
        assertEquals(cobranca.dataVencimento, LocalDate.of(2021, 10, 21))
        assertEquals(cobranca.status, StatusCobranca.ABERTO)
        assertEquals(cobranca.dataEmissao, LocalDate.of(2021, 10, 11))
        assertNull(cobranca.dataCancelamento)
        verify(repository, times(1)).save(any())
    }

    @Test
    fun `nao deve gerar cobranca no mesmo mes`() {
        Mockito.`when`(repository.existsInMonthByContratoAndByDateAndByStatusNotEquals(any(), any(), any())).thenReturn(true)
        val contrato = getContratoMockado()
        assertThrows(NegocioException::class.java) {
            service.registrarCobranca(contrato, LocalDate.now())
        }

    }
    @Test
    fun `nao deve gerar cobranca com data futura`() {
        Mockito.`when`(repository.existsInMonthByContratoAndByDateAndByStatusNotEquals(any(), any(), any())).thenReturn(false)
        val contrato = getContratoMockado()
        assertThrows(NegocioException::class.java) {
            service.registrarCobranca(contrato, LocalDate.now().plusDays(1L))
        }

    }

    @Test
    fun `deve cancelar a cobranca`() {
        val contrato = getContratoMockado()
        var cobranca = service.registrarCobranca(contrato, LocalDate.of(2021, 10, 11))
        Mockito.`when`(repository.findById(any())).thenReturn(cobranca)
        cobranca = service.cancelarCobranca(cobranca)
        assertEquals(cobranca.status, StatusCobranca.CANCELADO)
        assertNotNull(cobranca.dataCancelamento)
        assertEquals(cobranca.dataCancelamento, LocalDate.now())
    }


    private fun getContratoMockado(): Contrato {
        val contrato = Contrato(
            id = UUID.randomUUID(),
            procedimentos = mutableListOf(Procedimento(id = UUID.randomUUID()), Procedimento(id = UUID.randomUUID())),
            cirurgias = mutableListOf(Cirurgia(id = UUID.randomUUID()), Cirurgia(id = UUID.randomUUID())),
            dependentes = mutableListOf(
                Usuario(
                    id = UUID.randomUUID(),
                    plano = Plano(
                        id = UUID.randomUUID(),
                        valorBase = BigDecimal.valueOf(500)
                    ),
                    LocalDate.of(1997, 11, 6)
                ), Usuario(
                    id = UUID.randomUUID(),
                    plano = Plano(
                        id = UUID.randomUUID(),
                        valorBase = BigDecimal.valueOf(600)
                    ),
                    LocalDate.of(2000, 1, 1)
                )
            )
        )
        return contrato
    }
}