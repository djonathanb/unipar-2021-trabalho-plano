package br.unipar.plano.interfaces.rest.centrais

import br.unipar.plano.domain.centrais.services.CentralApplicationService
import br.unipar.plano.domain.centrais.usecases.impl.CentralNotFoundException
import br.unipar.plano.interfaces.rest.centrais.factories.centralDTO
import br.unipar.plano.interfaces.rest.centrais.factories.idCentral
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
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
    fun `deve criar uma nova central`() {
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
    fun `deve retornar 400 se nome em branco`() {
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
    fun `deve atualizar uma central`() {
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

}