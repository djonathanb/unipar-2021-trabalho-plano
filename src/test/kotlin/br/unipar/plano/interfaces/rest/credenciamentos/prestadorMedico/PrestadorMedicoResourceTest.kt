package br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico

import br.unipar.plano.application.exceptions.NotFoundException
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.factories.idPrestadorMedico
import br.unipar.plano.domain.credenciamentos.services.prestadorMedico.PrestMedAppService
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.factories.prestMedDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.factories.prestadorMedicoDetailsDTO
import br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico.factories.prestadorMedicoSummaryDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
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

private const val BASE_PATH = "/prestadorMedico"

@WebMvcTest(PrestadorMedicoResource::class)
class PrestadorMedicoResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var prestadormedicoApplicationService: PrestMedAppService

    @Test
    fun `deve retornar 201 e location ao criar uma nova prestadormedico`() {
        val idNovaPrestadorMedico = idPrestadorMedico()
        val localizacaoEsperada = "http://localhost$BASE_PATH/${idNovaPrestadorMedico.id}"
        val prestadormedicoDTO = prestMedDTO()

        `when`(prestadormedicoApplicationService.cria(any())).thenReturn(idNovaPrestadorMedico)

        val endpoint = BASE_PATH
        val conteudoJson = ObjectMapper().writeValueAsString(prestadormedicoDTO)

        val requisicao = post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isCreated)
            .andExpect(header().string("location", localizacaoEsperada))

        verify(prestadormedicoApplicationService).cria(eq(prestadormedicoDTO))
    }

    @Test
    fun `deve retornar 400 ao criar uma prestadormedico se nome em branco`() {
        val prestadormedicoDTO = prestadorMedicoDetailsDTO()

        val endpoint = BASE_PATH
        val conteudoJson = ObjectMapper().writeValueAsString(prestadormedicoDTO)

        val requisicao = post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isBadRequest)

        verify(prestadormedicoApplicationService, never()).cria(any())
    }

    @Test
    fun `deve retornar 204 ao atualizar uma prestadormedico`() {
        val idPrestadorMedico = idPrestadorMedico()
        val prestadormedicoDTO = prestMedDTO()

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(prestadormedicoDTO)

        val requisicao = put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(prestadormedicoApplicationService).atualiza(eq(idPrestadorMedico), eq(prestadormedicoDTO))
    }

    @Test
    fun `deve retornar 404 ao atualizar uma prestadormedico inexistente`() {
        val idPrestadorMedico = idPrestadorMedico()
        val prestadormedicoDTO = prestMedDTO()

        `when`(prestadormedicoApplicationService.atualiza(eq(idPrestadorMedico), eq(prestadormedicoDTO))).thenThrow(
            NotFoundException("")
        )

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(prestadormedicoDTO)

        val requisicao = put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 400 ao atualizar uma prestadormedico se nome em branco`() {
        val idPrestadorMedico = idPrestadorMedico()
        val prestadormedicoDTO = prestadorMedicoDetailsDTO()

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(prestadormedicoDTO)

        val requisicao = put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `deve retornar 204 ao deletar uma prestadormedico`() {
        val idPrestadorMedico = idPrestadorMedico()

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(prestadormedicoApplicationService).deleta(eq(idPrestadorMedico))
    }

    @Test
    fun `deve retornar 404 ao deletar uma prestadormedico inexistente`() {
        val idPrestadorMedico = idPrestadorMedico()

        `when`(prestadormedicoApplicationService.deleta(eq(idPrestadorMedico))).thenThrow(
            NotFoundException("")
        )

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao consultar uma prestadormedico`() {
        val idPrestadorMedico = idPrestadorMedico()
        val prestadormedicoDetailsDTO = prestadorMedicoDetailsDTO()

        `when`(prestadormedicoApplicationService.buscaPorId(eq(idPrestadorMedico))).thenReturn(prestadormedicoDetailsDTO)

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = ObjectMapper().writeValueAsString(prestadormedicoDetailsDTO)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

    @Test
    fun `deve retornar 404 ao consultar uma prestadormedico inexistente`() {
        val idPrestadorMedico = idPrestadorMedico()

        `when`(prestadormedicoApplicationService.buscaPorId(eq(idPrestadorMedico))).thenThrow(
            NotFoundException("")
        )

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val requisicao = get(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao listar as centrais`() {
        val listaCentrais = listOf(
            prestadorMedicoSummaryDTO(),
            prestadorMedicoSummaryDTO(staticId = false),
        )

        `when`(prestadormedicoApplicationService.lista()).thenReturn(listaCentrais)

        val endpoint = BASE_PATH
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = ObjectMapper().writeValueAsString(listaCentrais)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

    @Test
    fun `deve retornar 204 ao credenciar uma prestadormedico`() {
        val idPrestadorMedico = idPrestadorMedico()

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}/credenciamento"
        val requisicao = post(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(prestadormedicoApplicationService).credenciar(eq(idPrestadorMedico))
    }

    @Test
    fun `deve retornar 404 ao credenciar uma prestadormedico inexistente`() {
        val idPrestadorMedico = idPrestadorMedico()

        `when`(prestadormedicoApplicationService.credenciar(eq(idPrestadorMedico))).thenThrow(
            NotFoundException("")
        )

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}/credenciamento"
        val requisicao = post(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 204 ao descredenciar uma prestadormedico`() {
        val idPrestadorMedico = idPrestadorMedico()

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}/credenciamento"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(prestadormedicoApplicationService).descredenciar(eq(idPrestadorMedico))
    }

    @Test
    fun `deve retornar 404 ao descredenciar uma prestadormedico inexistente`() {
        val idPrestadorMedico = idPrestadorMedico()

        `when`(prestadormedicoApplicationService.descredenciar(eq(idPrestadorMedico))).thenThrow(
            NotFoundException("")
        )

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}/credenciamento"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

}