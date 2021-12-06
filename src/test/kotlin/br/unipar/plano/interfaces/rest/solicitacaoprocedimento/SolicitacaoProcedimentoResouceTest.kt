package br.unipar.plano.interfaces.rest.solicitacaoprocedimento

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoService
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

    @Test
    fun `deve retornar 201 e location ao criar uma nova solicitacao`() {
        val idNovaSolicitacao = IdSolicitacaoProcedimento()
        val localizacaoEsperada = "http://localhost${BASE_PATH}/${idNovaSolicitacao.id}"
        val solicitacaoDTO = SolicitacaoProcedimentoTestHelper()

        Mockito.`when`(solicitacaoProcedimentoService.insert(any())).thenReturn(idNovaSolicitacao)

        val endpoint = BASE_PATH
        val mapper = ObjectMapper();
        mapper.registerModule(JavaTimeModule())
        val conteudoJson = mapper.writeValueAsString(solicitacaoDTO)

        val requisicao = MockMvcRequestBuilders.post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.header().string("location", localizacaoEsperada))

        verify(solicitacaoProcedimentoService).insert(eq(solicitacaoDTO))
    }

//    @Test
//    fun `deve retornar 400 ao criar uma solicitacao sem status`() {
//        val solicitacaoDTO = SolicitacaoProcedimentoTestHelper(
//            nome = ""
//        )
//
//        val endpoint = BASE_PATH
//        val conteudoJson = ObjectMapper().writeValueAsString(solicitacaoDTO)
//
//        val requisicao = MockMvcRequestBuilders.post(endpoint)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(conteudoJson)
//
//        mockMvc.perform(requisicao)
//            .andExpect(MockMvcResultMatchers.status().isBadRequest)
//
//        verify(solicitacaoProcedimentoService, never()).insert(any())
//    }

    @Test
    fun `deve retornar 204 ao liberar uma solicitacao`() {
        val idSolicitacaoProcedimento = IdSolicitacaoProcedimento()
        val solicitacaoProcedimentoDTO = SolicitacaoProcedimentoTestHelper()

        val endpoint = "${BASE_PATH}/${idSolicitacaoProcedimento.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(solicitacaoProcedimentoDTO)

        val requisicao = MockMvcRequestBuilders.put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        verify(solicitacaoProcedimentoService).liberarSolicitacao(eq(idSolicitacaoProcedimento))
    }

//    @Test
//    fun `deve retornar 404 ao atualizar uma central inexistente`() {
//        val idSolicitacaoProcedimento = IdSolicitacaoProcedimento()
//        val solicitacaoProcedimentoDTO = SolicitacaoProcedimentoTestHelper()
//
//        Mockito.`when`(solicitacaoProcedimentoService.liberarSolicitacao(eq(idSolicitacaoProcedimento)))).thenThrow(
//
//        )
//
//        val endpoint = "${BASE_PATH}/${idSolicitacaoProcedimento.id}"
//        val conteudoJson = ObjectMapper().writeValueAsString(solicitacaoProcedimentoDTO)
//
//        val requisicao = MockMvcRequestBuilders.put(endpoint)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(conteudoJson)
//
//        mockMvc.perform(requisicao)
//            .andExpect(MockMvcResultMatchers.status().isNotFound)
//    }
}