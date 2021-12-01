package br.unipar.plano.interfaces.rest.credenciamentos.prestadorClinicaHospital.factories

import br.unipar.plano.domain.centrais.model.factories.idCentral
import br.unipar.plano.domain.centrais.services.CentralApplicationService
import br.unipar.plano.domain.centrais.usecases.impl.CentralNotFoundException
import br.unipar.plano.interfaces.rest.centrais.factories.centralDTO
import br.unipar.plano.interfaces.rest.centrais.factories.centralDetailsDTO
import br.unipar.plano.interfaces.rest.centrais.factories.centralSummaryDTO
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

private const val BASE_PATH = "/centrais"

@WebMvcTest(CentralResource::class)
class CentralResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var centralApplicationService: CentralApplicationService

    @Test
    fun `deve retornar 201 e location ao criar uma nova central`() {
        val idNovaCentral = idCentral()
        val localizacaoEsperada = "http://localhost$BASE_PATH/${idNovaCentral.id}"
        val centralDTO = centralDTO()

        `when`(centralApplicationService.cria(any())).thenReturn(idNovaCentral)

        val endpoint = BASE_PATH
        val conteudoJson = ObjectMapper().writeValueAsString(centralDTO)

        val requisicao = post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isCreated)
            .andExpect(header().string("location", localizacaoEsperada))

        verify(centralApplicationService).cria(eq(centralDTO))
    }

    @Test
    fun `deve retornar 400 ao criar uma central se nome em branco`() {
        val centralDTO = centralDTO(
            nome = ""
        )

        val endpoint = BASE_PATH
        val conteudoJson = ObjectMapper().writeValueAsString(centralDTO)

        val requisicao = post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isBadRequest)

        verify(centralApplicationService, never()).cria(any())
    }

    @Test
    fun `deve retornar 204 ao atualizar uma central`() {
        val idCentral = idCentral()
        val centralDTO = centralDTO()

        val endpoint = "$BASE_PATH/${idCentral.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(centralDTO)

        val requisicao = put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(centralApplicationService).atualiza(eq(idCentral), eq(centralDTO))
    }

    @Test
    fun `deve retornar 404 ao atualizar uma central inexistente`() {
        val idCentral = idCentral()
        val centralDTO = centralDTO()

        `when`(centralApplicationService.atualiza(eq(idCentral), eq(centralDTO))).thenThrow(
            CentralNotFoundException(idCentral)
        )

        val endpoint = "$BASE_PATH/${idCentral.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(centralDTO)

        val requisicao = put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 400 ao atualizar uma central se nome em branco`() {
        val idCentral = idCentral()
        val centralDTO = centralDTO(nome = "")

        val endpoint = "$BASE_PATH/${idCentral.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(centralDTO)

        val requisicao = put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `deve retornar 204 ao deletar uma central`() {
        val idCentral = idCentral()

        val endpoint = "$BASE_PATH/${idCentral.id}"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(centralApplicationService).deleta(eq(idCentral))
    }

    @Test
    fun `deve retornar 404 ao deletar uma central inexistente`() {
        val idCentral = idCentral()

        `when`(centralApplicationService.deleta(eq(idCentral))).thenThrow(
            CentralNotFoundException(idCentral)
        )

        val endpoint = "$BASE_PATH/${idCentral.id}"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao consultar uma central`() {
        val idCentral = idCentral()
        val centralDetailsDTO = centralDetailsDTO()

        whenever(centralApplicationService.buscaPorId(eq(idCentral))).thenReturn(centralDetailsDTO)

        val endpoint = "$BASE_PATH/${idCentral.id}"
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = ObjectMapper().writeValueAsString(centralDetailsDTO)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

    @Test
    fun `deve retornar 404 ao consultar uma central inexistente`() {
        val idCentral = idCentral()

        `when`(centralApplicationService.buscaPorId(eq(idCentral))).thenThrow(
            CentralNotFoundException(idCentral)
        )

        val endpoint = "$BASE_PATH/${idCentral.id}"
        val requisicao = get(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao listar as centrais`() {
        val listaCentrais = listOf(
            centralSummaryDTO(),
            centralSummaryDTO(staticId = false),
        )

        whenever(centralApplicationService.lista()).thenReturn(listaCentrais)

        val endpoint = BASE_PATH
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = ObjectMapper().writeValueAsString(listaCentrais)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

    @Test
    fun `deve retornar 204 ao credenciar uma central`() {
        val idCentral = idCentral()

        val endpoint = "$BASE_PATH/${idCentral.id}/credenciamento"
        val requisicao = post(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(centralApplicationService).credenciar(eq(idCentral))
    }

    @Test
    fun `deve retornar 404 ao credenciar uma central inexistente`() {
        val idCentral = idCentral()

        whenever(centralApplicationService.credenciar(eq(idCentral))).thenThrow(
            CentralNotFoundException(idCentral)
        )

        val endpoint = "$BASE_PATH/${idCentral.id}/credenciamento"
        val requisicao = post(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 204 ao descredenciar uma central`() {
        val idCentral = idCentral()

        val endpoint = "$BASE_PATH/${idCentral.id}/credenciamento"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(centralApplicationService).descredenciar(eq(idCentral))
    }

    @Test
    fun `deve retornar 404 ao descredenciar uma central inexistente`() {
        val idCentral = idCentral()

        whenever(centralApplicationService.descredenciar(eq(idCentral))).thenThrow(
            CentralNotFoundException(idCentral)
        )

        val endpoint = "$BASE_PATH/${idCentral.id}/credenciamento"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

}
