package br.unipar.plano.interfaces.rest.planos

import br.unipar.plano.domain.planos.model.factories.idPlano
import br.unipar.plano.domain.planos.services.PlanoApplicationService
import br.unipar.plano.domain.planos.usecases.impl.PlanoNotFoundException
import br.unipar.plano.interfaces.rest.planos.factories.planoDTO
import br.unipar.plano.interfaces.rest.planos.factories.planoDetailsDTO
import br.unipar.plano.interfaces.rest.planos.factories.planoSummaryDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.*
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

private const val BASE_PATH = "/planos"

@WebMvcTest(PlanoResource::class, properties = ["inserir.contrato.no.startup=false"])
class PlanoResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var planoApplicationService: PlanoApplicationService

    @Test
    fun `deve retornar 201 e location ao criar um novo plano`() {
        val idNovoPlano = idPlano()
        val localizacaoEsperada = "http://localhost$BASE_PATH/${idNovoPlano.id}"
        val planoDTO = planoDTO()

        `when`(planoApplicationService.cria(any())).thenReturn(idNovoPlano)

        val endpoint = BASE_PATH
        val conteudoJson = ObjectMapper().writeValueAsString(planoDTO)

        val requisicao = post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isCreated)
            .andExpect(header().string("location", localizacaoEsperada))

        verify(planoApplicationService).cria(eq(planoDTO))
    }

    @Test
    fun `deve retornar 400 ao criar um plano se nome em branco`() {
        val planoDTO = planoDTO(
            nome = ""
        )

        val endpoint = BASE_PATH
        val conteudoJson = ObjectMapper().writeValueAsString(planoDTO)

        val requisicao = post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isBadRequest)

        verify(planoApplicationService, never()).cria(any())
    }

    @Test
    fun `deve retornar 204 ao deletar um plano`() {
        val idPlano = idPlano()

        val endpoint = "$BASE_PATH/${idPlano.id}"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(planoApplicationService).deleta(eq(idPlano))
    }

    @Test
    fun `deve retornar 404 ao deletar uma central inexistente`() {
        val idPlano = idPlano()

        `when`(planoApplicationService.deleta(eq(idPlano))).thenThrow(
            PlanoNotFoundException(idPlano)
        )

        val endpoint = "$BASE_PATH/${idPlano.id}"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao consultar uma central`() {
        val idPlano = idPlano()
        val planoDetailsDTO = planoDetailsDTO()

        whenever(planoApplicationService.buscaPorId(eq(idPlano))).thenReturn(planoDetailsDTO)

        val endpoint = "$BASE_PATH/${idPlano.id}"
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = ObjectMapper().writeValueAsString(planoDetailsDTO)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

    @Test
    fun `deve retornar 404 ao consultar uma central inexistente`() {
        val idPlano = idPlano()

        `when`(planoApplicationService.buscaPorId(eq(idPlano))).thenThrow(
            PlanoNotFoundException(idPlano)
        )

        val endpoint = "$BASE_PATH/${idPlano.id}"
        val requisicao = get(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao listar os planos`() {
        val listaPlanos = listOf(
            planoSummaryDTO(),
            planoSummaryDTO(staticId = false),
        )

        whenever(planoApplicationService.lista()).thenReturn(listaPlanos)

        val endpoint = BASE_PATH
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = ObjectMapper().writeValueAsString(listaPlanos)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

}
