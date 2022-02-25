package br.unipar.plano.domain.cobrancas.usecases.impl

import br.unipar.plano.application.exceptions.NegocioException
import br.unipar.plano.domain.cobrancas.model.Cirurgia
import br.unipar.plano.domain.cobrancas.model.Cobranca
import br.unipar.plano.domain.cobrancas.model.Procedimento
import br.unipar.plano.domain.cobrancas.model.factories.*
import br.unipar.plano.domain.cobrancas.repository.CobrancaRepository
import br.unipar.plano.domain.cobrancas.repository.ContratoRepository
import br.unipar.plano.domain.cobrancas.service.impl.ContratoNotFoundException
import br.unipar.plano.domain.cobrancas.valueobjects.StatusCobranca
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class RegistrarCobrancaUseCaseImplTest {

    private var repository = mock<CobrancaRepository>()
    private var contratoRepository = mock<ContratoRepository>()
    private var registrarCobrancaUseCaseImpl =
        RegistrarCobrancaUseCaseImpl(
            repository,
            contratoRepository,
            BigDecimal.valueOf(1000),
            BigDecimal.valueOf(10),
            10
        )

    @BeforeEach
    fun setup() {
        Mockito.`when`(repository.save(any())).doAnswer { it -> it.arguments[0] as Cobranca? }
        Mockito.`when`(contratoRepository.findById(any())).thenReturn(Optional.of(contrato()))
    }

    @Test
    fun `deve registrar uma cobranca`() {
        val contrato = contrato()
        val procedimentos: Collection<Procedimento> = mutableListOf(
            Procedimento(id = PROCEDIMENTO_ID_1),
            Procedimento(id = PROCEDIMENTO_ID_2)
        )
        val cirurgias: Collection<Cirurgia> = mutableListOf(Cirurgia(id = CIRURGIA_ID_1), Cirurgia(id = CIRURGIA_ID_2))
        val cobranca =
            registrarCobrancaUseCaseImpl.executa(contrato.id, DATA_EMISSAO_COBRANCA, cirurgias, procedimentos)
        assertNotNull(cobranca)
        assertEquals(
            cobranca.valorTotal,
            BigDecimal.valueOf(1100) // valor dos planos
                .add(BigDecimal.valueOf(2000)) // valor adicional das cirurgias
                .add(BigDecimal.valueOf(20)) // valor Adicional dos procedimentos
                .add(
                    BigDecimal.valueOf(
                        ChronoUnit.YEARS.between(LocalDate.of(1997, 11, 6), LocalDate.now())
                                + ChronoUnit.YEARS.between(LocalDate.of(2000, 1, 1), LocalDate.now())
                    )
                )// valor adicional das idades
                .setScale(2, RoundingMode.HALF_UP)
        )
        assertEquals(cobranca.dataVencimento, DATA_VENCIMENTO_COBRANCA)
        assertEquals(cobranca.status, StatusCobranca.ABERTO)
        assertEquals(cobranca.dataEmissao, DATA_EMISSAO_COBRANCA)
        assertNull(cobranca.dataCancelamento)
        verify(repository, times(1)).save(any())
    }

    @Test
    fun `nao deve gerar cobranca com data futura`() {
        Mockito.`when`(repository.existsInMonthByContratoAndDateAndStatusNotEquals(any(), any(), any()))
            .thenReturn(false)
        val contrato = contrato()
        val procedimentos: Collection<Procedimento> = mutableListOf(
            Procedimento(id = PROCEDIMENTO_ID_1),
            Procedimento(id = PROCEDIMENTO_ID_2)
        )
        val cirurgias: Collection<Cirurgia> = mutableListOf(Cirurgia(id = CIRURGIA_ID_1), Cirurgia(id = CIRURGIA_ID_2))
        assertThrows(NegocioException::class.java) {
            registrarCobrancaUseCaseImpl.executa(contrato.id, LocalDate.now().plusDays(1L), cirurgias, procedimentos)
        }

    }

    @Test
    fun `nao deve gerar uma cobranca pois o contrato nao existe`() {
        Mockito.`when`(repository.existsInMonthByContratoAndDateAndStatusNotEquals(any(), any(), any()))
            .thenReturn(false)
        Mockito.`when`(contratoRepository.findById(any())).thenReturn(Optional.empty())
        val contrato = contrato()
        val procedimentos: Collection<Procedimento> = mutableListOf(
            Procedimento(id = PROCEDIMENTO_ID_1),
            Procedimento(id = PROCEDIMENTO_ID_2)
        )
        val cirurgias: Collection<Cirurgia> = mutableListOf(Cirurgia(id = CIRURGIA_ID_1), Cirurgia(id = CIRURGIA_ID_2))
        assertThrows(ContratoNotFoundException::class.java) {
            registrarCobrancaUseCaseImpl.executa(contrato.id, LocalDate.now(), cirurgias, procedimentos)
        }
    }
}