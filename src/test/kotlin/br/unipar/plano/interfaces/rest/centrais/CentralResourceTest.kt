package br.unipar.plano.interfaces.rest.centrais

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.centrais.services.CentralApplicationService
import br.unipar.plano.interfaces.rest.centrais.factories.centralDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
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
        val idNovaCentral = IdCentral()
        val localizacaoEsperada = "http://localhost$BASE_PATH/${idNovaCentral.id}"

        `when`(centralApplicationService.criar(any())).thenReturn(idNovaCentral)

        val centralDTO = centralDTO()

        val conteudoJson = ObjectMapper().writeValueAsString(centralDTO)
        val requisicao = post(BASE_PATH).contentType(MediaType.APPLICATION_JSON).content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isCreated)
            .andExpect(header().string("location", localizacaoEsperada))

        verify(centralApplicationService).criar(eq(centralDTO))
    }

    @Test
    fun `deve retornar 400 se nome em branco`() {
        val centralDTO = centralDTO(
            nome = ""
        )

        val conteudoJson = ObjectMapper().writeValueAsString(centralDTO)
        val requisicao = post(BASE_PATH).contentType(MediaType.APPLICATION_JSON).content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isBadRequest)

        verify(centralApplicationService, never()).criar(any())
    }

}