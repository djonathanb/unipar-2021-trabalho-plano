package br.unipar.plano.interfaces.rest.transportes

import br.unipar.plano.domain.centrais.model.factories.idTransporte
import br.unipar.plano.domain.centrais.services.TransporteApplicationService
import br.unipar.plano.interfaces.rest.transportes.factories.transporteDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
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

private const val BASE_PATH = "/transportes"

@WebMvcTest(TransporteResource::class)
class TransporteResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var transporteApplicationService: TransporteApplicationService

    @Test
    fun `deve retornar 201 e location ao criar um novo transporte`() {
        val idNovaTransporte = idTransporte()
        val localizacaoEsperada = "http://localhost$BASE_PATH/${idNovaTransporte.id}"
        val transporteDTO = transporteDTO()

        `when`(transporteApplicationService.cria(any())).thenReturn(idNovaTransporte)

        val endpoint = BASE_PATH
        val conteudoJson = ObjectMapper().writeValueAsString(transporteDTO)

        val requisicao = post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isCreated)
            .andExpect(header().string("location", localizacaoEsperada))

        verify(transporteApplicationService).cria(eq(transporteDTO))
    }

//    @Test
//    fun `deve retornar 400 ao criar uma transporte se nome em branco`() {
//        val transporteDTO = transporteDTO(
//            nome = ""
//        )
//
//        val endpoint = BASE_PATH
//        val conteudoJson = ObjectMapper().writeValueAsString(transporteDTO)
//
//        val requisicao = post(endpoint)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(conteudoJson)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isBadRequest)
//
//        verify(transporteApplicationService, never()).cria(any())
//    }
//
//    @Test
//    fun `deve retornar 204 ao atualizar uma transporte`() {
//        val idTransporte = idTransporte()
//        val transporteDTO = transporteDTO()
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}"
//        val conteudoJson = ObjectMapper().writeValueAsString(transporteDTO)
//
//        val requisicao = put(endpoint)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(conteudoJson)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isNoContent)
//
//        verify(transporteApplicationService).atualiza(eq(idTransporte), eq(transporteDTO))
//    }
//
//    @Test
//    fun `deve retornar 404 ao atualizar uma transporte inexistente`() {
//        val idTransporte = idTransporte()
//        val transporteDTO = transporteDTO()
//
//        `when`(transporteApplicationService.atualiza(eq(idTransporte), eq(transporteDTO))).thenThrow(
//            TransporteNotFoundException(idTransporte)
//        )
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}"
//        val conteudoJson = ObjectMapper().writeValueAsString(transporteDTO)
//
//        val requisicao = put(endpoint)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(conteudoJson)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isNotFound)
//    }
//
//    @Test
//    fun `deve retornar 400 ao atualizar uma transporte se nome em branco`() {
//        val idTransporte = idTransporte()
//        val transporteDTO = transporteDTO(nome = "")
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}"
//        val conteudoJson = ObjectMapper().writeValueAsString(transporteDTO)
//
//        val requisicao = put(endpoint)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(conteudoJson)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isBadRequest)
//    }
//
//    @Test
//    fun `deve retornar 204 ao deletar uma transporte`() {
//        val idTransporte = idTransporte()
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}"
//        val requisicao = delete(endpoint)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isNoContent)
//
//        verify(transporteApplicationService).deleta(eq(idTransporte))
//    }
//
//    @Test
//    fun `deve retornar 404 ao deletar uma transporte inexistente`() {
//        val idTransporte = idTransporte()
//
//        `when`(transporteApplicationService.deleta(eq(idTransporte))).thenThrow(
//            TransporteNotFoundException(idTransporte)
//        )
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}"
//        val requisicao = delete(endpoint)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isNotFound)
//    }
//
//    @Test
//    fun `deve retornar 200 e corpo ao consultar uma transporte`() {
//        val idTransporte = idTransporte()
//        val transporteDetailsDTO = transporteDetailsDTO()
//
//        whenever(transporteApplicationService.buscaPorId(eq(idTransporte))).thenReturn(transporteDetailsDTO)
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}"
//        val requisicao = get(endpoint)
//
//        val mvcResult = mockMvc.perform(requisicao)
//            .andExpect(status().isOk)
//            .andReturn()
//
//        val resultadoEsperado = ObjectMapper().writeValueAsString(transporteDetailsDTO)
//        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
//    }
//
//    @Test
//    fun `deve retornar 404 ao consultar uma transporte inexistente`() {
//        val idTransporte = idTransporte()
//
//        `when`(transporteApplicationService.buscaPorId(eq(idTransporte))).thenThrow(
//            TransporteNotFoundException(idTransporte)
//        )
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}"
//        val requisicao = get(endpoint)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isNotFound)
//    }
//
//    @Test
//    fun `deve retornar 200 e corpo ao listar as centrais`() {
//        val listaCentrais = listOf(
//            transporteSummaryDTO(),
//            transporteSummaryDTO(staticId = false),
//        )
//
//        whenever(transporteApplicationService.lista()).thenReturn(listaCentrais)
//
//        val endpoint = BASE_PATH
//        val requisicao = get(endpoint)
//
//        val mvcResult = mockMvc.perform(requisicao)
//            .andExpect(status().isOk)
//            .andReturn()
//
//        val resultadoEsperado = ObjectMapper().writeValueAsString(listaCentrais)
//        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
//    }
//
//    @Test
//    fun `deve retornar 204 ao credenciar uma transporte`() {
//        val idTransporte = idTransporte()
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}/credenciamento"
//        val requisicao = post(endpoint)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isNoContent)
//
//        verify(transporteApplicationService).credenciar(eq(idTransporte))
//    }
//
//    @Test
//    fun `deve retornar 404 ao credenciar uma transporte inexistente`() {
//        val idTransporte = idTransporte()
//
//        whenever(transporteApplicationService.credenciar(eq(idTransporte))).thenThrow(
//            TransporteNotFoundException(idTransporte)
//        )
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}/credenciamento"
//        val requisicao = post(endpoint)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isNotFound)
//    }
//
//    @Test
//    fun `deve retornar 204 ao descredenciar uma transporte`() {
//        val idTransporte = idTransporte()
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}/credenciamento"
//        val requisicao = delete(endpoint)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isNoContent)
//
//        verify(transporteApplicationService).descredenciar(eq(idTransporte))
//    }
//
//    @Test
//    fun `deve retornar 404 ao descredenciar uma transporte inexistente`() {
//        val idTransporte = idTransporte()
//
//        whenever(transporteApplicationService.descredenciar(eq(idTransporte))).thenThrow(
//            TransporteNotFoundException(idTransporte)
//        )
//
//        val endpoint = "$BASE_PATH/${idTransporte.id}/credenciamento"
//        val requisicao = delete(endpoint)
//
//        mockMvc.perform(requisicao)
//            .andExpect(status().isNotFound)
//    }

}
