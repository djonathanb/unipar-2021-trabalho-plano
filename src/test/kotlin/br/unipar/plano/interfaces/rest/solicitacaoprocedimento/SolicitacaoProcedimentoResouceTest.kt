package br.unipar.plano.interfaces.rest.solicitacaoprocedimento

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.model.factories.idSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoService
import br.unipar.plano.domain.solicitacaoprocedimento.usecases.impl.SolicitacaoProcedimentoNotFoundException
import br.unipar.plano.interfaces.rest.solicitacaoprocedimento.factories.SolicitacaoProcedimentoTestHelper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


private const val BASE_PATH = "/solicitacao-liberacao-procedimento"

@WebMvcTest(SolicitacaoProcedimentoResource::class)
class SolicitacaoProcedimentoResouceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var solicitacaoProcedimentoService: SolicitacaoProcedimentoService

    //dificultades com a implantacao
    fun `deve retornar 201 e location ao criar uma nova solicitacao`() {
        val idSolicitacaoProcedimento = idSolicitacaoProcedimento()
        val localizacaoEsperada = "http://localhost${BASE_PATH}/${idSolicitacaoProcedimento.id}"
        val solicitacaoProcedimentoDTO = SolicitacaoProcedimentoTestHelper()

        Mockito.`when`(solicitacaoProcedimentoService.insert(any())).thenReturn(idSolicitacaoProcedimento)

        val endpoint = "${BASE_PATH}/criar"
        val mapper = ObjectMapper();
        mapper.registerModule(JavaTimeModule())
        val conteudoJson = mapper.writeValueAsString(solicitacaoProcedimentoDTO)

        val requisicao = MockMvcRequestBuilders.post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.header().string("location", localizacaoEsperada))

        verify(solicitacaoProcedimentoService).insert(eq(solicitacaoProcedimentoDTO))
    }

    //dificultades com a implantacao
    fun `deve retornar 204 ao rejeitar uma solicitacao`() {
        val idSolicitacaoProcedimento = IdSolicitacaoProcedimento()
        val solicitacaoProcedimentoDTO = SolicitacaoProcedimentoTestHelper()

        Mockito.doNothing().`when`(solicitacaoProcedimentoService).rejeitarSolicitacao(any(), any());

        val endpoint = "${BASE_PATH}/rejeitar/${idSolicitacaoProcedimento.id}"
        val mapper = ObjectMapper();
        mapper.registerModule(JavaTimeModule())
        val conteudoJson = mapper.writeValueAsString(solicitacaoProcedimentoDTO)

        val requisicao = MockMvcRequestBuilders.put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        verify(solicitacaoProcedimentoService).rejeitarSolicitacao(eq(idSolicitacaoProcedimento), eq(any()))
    }

    //dificultades com a implantacao
    fun `deve retornar 204 ao liberar uma solicitacao`() {
        val idSolicitacaoProcedimento = IdSolicitacaoProcedimento()
        val solicitacaoProcedimentoDTO = SolicitacaoProcedimentoTestHelper()

        Mockito.doNothing().`when`(solicitacaoProcedimentoService).liberarSolicitacao(any());

        val endpoint = "${BASE_PATH}/liberar/${idSolicitacaoProcedimento.id}"
        val mapper = ObjectMapper();
        mapper.registerModule(JavaTimeModule())
        val conteudoJson = mapper.writeValueAsString(solicitacaoProcedimentoDTO)

        val requisicao = MockMvcRequestBuilders.put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        verify(solicitacaoProcedimentoService).liberarSolicitacao(eq(idSolicitacaoProcedimento))
    }

    @Test
    fun `deve retornar 500 ao liberar uma solicitacao inexistente`() {
        val idSolicitacaoProcedimento = IdSolicitacaoProcedimento()
        val solicitacaoProcedimentoDTO = SolicitacaoProcedimentoTestHelper()

        Mockito.`when`(solicitacaoProcedimentoService.liberarSolicitacao(eq(idSolicitacaoProcedimento))).thenThrow(
            SolicitacaoProcedimentoNotFoundException(idSolicitacaoProcedimento)
        )

        val endpoint = "${BASE_PATH}/liberar/${idSolicitacaoProcedimento.id}"
        val mapper = ObjectMapper();
        mapper.registerModule(JavaTimeModule())
        val conteudoJson = mapper.writeValueAsString(solicitacaoProcedimentoDTO)

        val requisicao = MockMvcRequestBuilders.put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isInternalServerError)
    }

    @Test
    fun `deve retornar 204 ao deletar uma solicitacao`() {
        val idSolicitacaoProcedimento = IdSolicitacaoProcedimento()

        val endpoint = "${BASE_PATH}/${idSolicitacaoProcedimento.id}"
        val requisicao = MockMvcRequestBuilders.delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        verify(solicitacaoProcedimentoService).deleta(eq(idSolicitacaoProcedimento))
    }
}