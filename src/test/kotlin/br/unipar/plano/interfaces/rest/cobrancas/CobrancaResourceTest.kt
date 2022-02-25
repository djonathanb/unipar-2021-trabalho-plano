package br.unipar.plano.interfaces.rest.cobrancas

import br.unipar.plano.domain.cobrancas.model.factories.CONTRATO_UUID
import br.unipar.plano.domain.cobrancas.model.factories.cobranca
import br.unipar.plano.domain.cobrancas.model.factories.idCobranca
import br.unipar.plano.domain.cobrancas.model.factories.idContrato
import br.unipar.plano.domain.cobrancas.service.CobrancaService
import br.unipar.plano.domain.cobrancas.service.impl.CobrancaNotFoundException
import br.unipar.plano.interfaces.rest.cobrancas.factories.cobrancaDetailsDTO
import br.unipar.plano.interfaces.rest.cobrancas.factories.cobrancaSummaryDTO
import br.unipar.plano.interfaces.rest.cobrancas.factories.registrarCobrancaDTO
import br.unipar.plano.interfaces.rest.cobrancas.impl.CobrancaResourceImpl
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

private val BASE_PATH = "/contratos/${CONTRATO_UUID}/cobrancas"
private const val CANCELAMENTO_PATH = "/cancelamento"

@WebMvcTest(CobrancaResourceImpl::class, properties = ["inserir.contrato.no.startup=false"])
class CobrancaResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var cobrancaService: CobrancaService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `deve retornar 201 e o endereco pra consultar o recurso`() {
        val cobranca = cobranca()
        val idCobranca = cobranca.id
        val localizacaoEsperada = "http://localhost$BASE_PATH/${idCobranca.id}"
        val registrarCobrancaDTO = registrarCobrancaDTO()
        `when`(
            cobrancaService.registrarCobranca(
                any(),
                any(), any(),
                any()
            )
        ).thenReturn(idCobranca)

        val endpoint = BASE_PATH
        val conteudoJson = objectMapper.writeValueAsString(registrarCobrancaDTO)

        val requisicao = post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isCreated)
            .andExpect(header().string("location", localizacaoEsperada))

    }

    @Test
    fun `deve retornar 200 ao cancelar  uma cobranca`() {
        val idCobranca = idCobranca()
        val idContrato = idContrato()
        val endpoint = "$BASE_PATH/${idCobranca.id}$CANCELAMENTO_PATH"
        val requisicao = patch(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isOk)

        verify(cobrancaService).cancelarCobranca(eq(idContrato), eq(idCobranca))
    }

    @Test
    fun `deve retornar 404 ao deletar uma cobranca inexistente`() {
        val idCobranca = idCobranca()
        val idContrato = idContrato()
        `when`(cobrancaService.cancelarCobranca(eq(idContrato), eq(idCobranca))).thenThrow(
            CobrancaNotFoundException(idCobranca)
        )

        val endpoint = "$BASE_PATH/${idCobranca.id}$CANCELAMENTO_PATH"
        val requisicao = patch(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao consultar uma cobranca`() {
        val idCobranca = idCobranca()
        val idContrato = idContrato()
        val cobrancaDetailsDTO = cobrancaDetailsDTO()

        whenever(cobrancaService.buscarPorId(eq(idContrato), eq(idCobranca))).thenReturn(cobrancaDetailsDTO)

        val endpoint = "$BASE_PATH/${idCobranca.id}"
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = objectMapper.writeValueAsString(cobrancaDetailsDTO)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

    @Test
    fun `deve retornar 404 ao consultar uma cobranca inexistente`() {
        val idCobranca = idCobranca()
        val idContrato = idContrato()

        `when`(cobrancaService.buscarPorId(eq(idContrato), eq(idCobranca))).thenThrow(
            CobrancaNotFoundException(idCobranca)
        )

        val endpoint = "$BASE_PATH/${idCobranca.id}"
        val requisicao = get(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao listar as centrais`() {
        val listaCentrais = listOf(
            cobrancaSummaryDTO(),
            cobrancaSummaryDTO(staticId = false),
        )
        val idContrato = idContrato()
        whenever(cobrancaService.buscaTodos(idContrato)).thenReturn(listaCentrais)

        val endpoint = BASE_PATH
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = objectMapper.writeValueAsString(listaCentrais)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }


}
