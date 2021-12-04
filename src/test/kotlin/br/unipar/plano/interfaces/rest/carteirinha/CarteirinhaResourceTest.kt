package br.unipar.plano.interfaces.rest.carteirinha

import br.unipar.plano.domain.carteirinha.model.factories.CARTEIRINHA_NUMERO_CARTEIRINHA_VALIDO
import br.unipar.plano.domain.carteirinha.services.CarteirinhaApplicationService
import br.unipar.plano.interfaces.rest.carteirinha.factories.carteirinhaDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header

private const val BASE_PATH = "/carteirinha"

@WebMvcTest(CarteirinhaResource::class)
class CarteirinhaResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var carteirinhaApplicationService: CarteirinhaApplicationService

    @Test
    fun `Deve retornar 201 e location ao criar uma nova carteirinha`() {
        val localizacaoEsperada = "http://localhost$BASE_PATH/$CARTEIRINHA_NUMERO_CARTEIRINHA_VALIDO"
        val dto = carteirinhaDTO()

        `when`(carteirinhaApplicationService.criar(any())).thenReturn(CARTEIRINHA_NUMERO_CARTEIRINHA_VALIDO)

        val json = ObjectMapper().writeValueAsString(dto)

        val requisicao = post(BASE_PATH).contentType(MediaType.APPLICATION_JSON).content(json)

        mockMvc.perform(requisicao).andExpect(status().isCreated).andExpect(header().string("location", localizacaoEsperada))

    }

}
