package br.unipar.plano.interfaces.rest.transportes

import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.domain.centrais.services.TransporteApplicationService
import br.unipar.plano.domain.transportes.model.TipoTransporte
import br.unipar.plano.domain.transportes.model.factories.idTransporte
import br.unipar.plano.domain.transportes.usecases.impl.TransporteNotFoundException
import br.unipar.plano.interfaces.rest.carteirinhas.CarteirinhaSummaryDTO
import br.unipar.plano.interfaces.rest.transportes.factories.transporteDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

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

    @Test
    fun `deve retornar 201 e location ao criar uma nova Transporte`() {
        val idNovoTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))
        val localizacaoEsperada = "http://localhost$BASE_PATH/${idNovoTransporte.id}"
        val transporteDTO = TransporteDTO(
            carteirinha = CarteirinhaSummaryDTO(id = TRANSPORTE_CARTEIRINHA_ID),
            enderecoOrigem = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ENDERECO_ORIGEM_CIDADE,
                cep = TRANSPORTE_ENDERECO_ORIGEM_CEP,
                bairro = TRANSPORTE_ENDERECO_ORIGEM_BAIRRO,
                logradouro = TRANSPORTE_ENDERECO_ORIGEM_LOGRADOURO,
                numero = TRANSPORTE_ENDERECO_ORIGEM_NUMERO,
                complemento = TRANSPORTE_ENDERECO_ORIGEM_COMPLEMENTO
            ),
            enderecoDestino = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ENDERECO_DESTINO_CIDADE,
                cep = TRANSPORTE_ENDERECO_DESTINO_CEP,
                bairro = TRANSPORTE_ENDERECO_DESTINO_BAIRRO,
                logradouro = TRANSPORTE_ENDERECO_DESTINO_LOGRADOURO,
                numero = TRANSPORTE_ENDERECO_DESTINO_NUMERO,
                complemento = TRANSPORTE_ENDERECO_DESTINO_COMPLEMENTO
            ),
            tipoTransporte = TipoTransporte.AMBULANCIA
        )

        `when`(transporteApplicationService.cria(any())).thenReturn(idNovoTransporte)

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


    @Test
    fun `deve retornar 204 ao atualizar uma Transporte`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))
        val transporteDTO = TransporteDTO(
            carteirinha = CarteirinhaSummaryDTO(id = TRANSPORTE_CARTEIRINHA_ID),
            enderecoOrigem = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ENDERECO_ORIGEM_CIDADE,
                cep = TRANSPORTE_ENDERECO_ORIGEM_CEP,
                bairro = TRANSPORTE_ENDERECO_ORIGEM_BAIRRO,
                logradouro = TRANSPORTE_ENDERECO_ORIGEM_LOGRADOURO,
                numero = TRANSPORTE_ENDERECO_ORIGEM_NUMERO,
                complemento = TRANSPORTE_ENDERECO_ORIGEM_COMPLEMENTO
            ),
            enderecoDestino = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ENDERECO_DESTINO_CIDADE,
                cep = TRANSPORTE_ENDERECO_DESTINO_CEP,
                bairro = TRANSPORTE_ENDERECO_DESTINO_BAIRRO,
                logradouro = TRANSPORTE_ENDERECO_DESTINO_LOGRADOURO,
                numero = TRANSPORTE_ENDERECO_DESTINO_NUMERO,
                complemento = TRANSPORTE_ENDERECO_DESTINO_COMPLEMENTO
            ),
            tipoTransporte = TipoTransporte.AMBULANCIA
        )

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(transporteDTO)

        val requisicao = MockMvcRequestBuilders.put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(transporteApplicationService).cria(eq(transporteDTO))
    }

    @Test
    fun `deve retornar 404 ao atualizar um transporte inexistente`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))
        val transporteDTO = TransporteDTO(
            carteirinha = CarteirinhaSummaryDTO(id = TRANSPORTE_CARTEIRINHA_ID),
            enderecoOrigem = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ENDERECO_ORIGEM_CIDADE,
                cep = TRANSPORTE_ENDERECO_ORIGEM_CEP,
                bairro = TRANSPORTE_ENDERECO_ORIGEM_BAIRRO,
                logradouro = TRANSPORTE_ENDERECO_ORIGEM_LOGRADOURO,
                numero = TRANSPORTE_ENDERECO_ORIGEM_NUMERO,
                complemento = TRANSPORTE_ENDERECO_ORIGEM_COMPLEMENTO
            ),
            enderecoDestino = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ENDERECO_DESTINO_CIDADE,
                cep = TRANSPORTE_ENDERECO_DESTINO_CEP,
                bairro = TRANSPORTE_ENDERECO_DESTINO_BAIRRO,
                logradouro = TRANSPORTE_ENDERECO_DESTINO_LOGRADOURO,
                numero = TRANSPORTE_ENDERECO_DESTINO_NUMERO,
                complemento = TRANSPORTE_ENDERECO_DESTINO_COMPLEMENTO
            ),
            tipoTransporte = TipoTransporte.AMBULANCIA
        )

        `when`(transporteApplicationService.cria(eq(transporteDTO))).thenThrow(
            TransporteNotFoundException(idTransporte)
        )

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(transporteDTO)

        val requisicao = MockMvcRequestBuilders.put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 204 ao deletar uma Transporte`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val requisicao = MockMvcRequestBuilders.delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(transporteApplicationService).deleta(eq(idTransporte))
    }

    @Test
    fun `deve retornar 404 ao deletar uma Transporte inexistente`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))

        `when`(transporteApplicationService.deleta(eq(idTransporte))).thenThrow(
            TransporteNotFoundException(idTransporte)
        )

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val requisicao = MockMvcRequestBuilders.delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao consultar uma Transporte`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))
        val transporteDTO = TransporteDetailsDTO(
            id = idTransporte,
            transporteData = TransporteDTO(
                carteirinha = CarteirinhaSummaryDTO(id = TRANSPORTE_CARTEIRINHA_ID),
                enderecoOrigem = EnderecoTransporteDTO(
                    cidade = TRANSPORTE_ENDERECO_ORIGEM_CIDADE,
                    cep = TRANSPORTE_ENDERECO_ORIGEM_CEP,
                    bairro = TRANSPORTE_ENDERECO_ORIGEM_BAIRRO,
                    logradouro = TRANSPORTE_ENDERECO_ORIGEM_LOGRADOURO,
                    numero = TRANSPORTE_ENDERECO_ORIGEM_NUMERO,
                    complemento = TRANSPORTE_ENDERECO_ORIGEM_COMPLEMENTO
                ),
                enderecoDestino = EnderecoTransporteDTO(
                    cidade = TRANSPORTE_ENDERECO_DESTINO_CIDADE,
                    cep = TRANSPORTE_ENDERECO_DESTINO_CEP,
                    bairro = TRANSPORTE_ENDERECO_DESTINO_BAIRRO,
                    logradouro = TRANSPORTE_ENDERECO_DESTINO_LOGRADOURO,
                    numero = TRANSPORTE_ENDERECO_DESTINO_NUMERO,
                    complemento = TRANSPORTE_ENDERECO_DESTINO_COMPLEMENTO
                ),
                tipoTransporte = TipoTransporte.AMBULANCIA
            )
        )


        whenever(transporteApplicationService.buscaPorId(eq(idTransporte))).thenReturn(transporteDTO)

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val requisicao = MockMvcRequestBuilders.get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = ObjectMapper().writeValueAsString(transporteDTO)
        Assertions.assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

    @Test
    fun `deve retornar 404 ao consultar uma Transporte inexistente`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))

        `when`(transporteApplicationService.buscaPorId(eq(idTransporte))).thenThrow(
            TransporteNotFoundException(idTransporte)
        )

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val requisicao = MockMvcRequestBuilders.get(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }


    @Test
    fun `deve retornar 404 ao credenciar uma Transporte inexistente`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))
        val transporteDetailsDTO = TransporteDTO(

            carteirinha = CarteirinhaSummaryDTO(id = TRANSPORTE_CARTEIRINHA_ID),
            enderecoOrigem = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ENDERECO_ORIGEM_CIDADE,
                cep = TRANSPORTE_ENDERECO_ORIGEM_CEP,
                bairro = TRANSPORTE_ENDERECO_ORIGEM_BAIRRO,
                logradouro = TRANSPORTE_ENDERECO_ORIGEM_LOGRADOURO,
                numero = TRANSPORTE_ENDERECO_ORIGEM_NUMERO,
                complemento = TRANSPORTE_ENDERECO_ORIGEM_COMPLEMENTO
            ),
            enderecoDestino = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ENDERECO_DESTINO_CIDADE,
                cep = TRANSPORTE_ENDERECO_DESTINO_CEP,
                bairro = TRANSPORTE_ENDERECO_DESTINO_BAIRRO,
                logradouro = TRANSPORTE_ENDERECO_DESTINO_LOGRADOURO,
                numero = TRANSPORTE_ENDERECO_DESTINO_NUMERO,
                complemento = TRANSPORTE_ENDERECO_DESTINO_COMPLEMENTO
            ),
            tipoTransporte = TipoTransporte.AMBULANCIA
        )

        whenever(transporteApplicationService.aprova((transporteDetailsDTO))).thenThrow(
            TransporteNotFoundException(idTransporte)
        )

        val endpoint = "$BASE_PATH/${idTransporte.id}/credenciamento"
        val requisicao = post(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }


    @Test
    fun `deve retornar 404 ao descredenciar uma Transporte inexistente`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))
        val transporteDetailsDTO = TransporteDTO(

            carteirinha = CarteirinhaSummaryDTO(id = TRANSPORTE_CARTEIRINHA_ID),
            enderecoOrigem = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ENDERECO_ORIGEM_CIDADE,
                cep = TRANSPORTE_ENDERECO_ORIGEM_CEP,
                bairro = TRANSPORTE_ENDERECO_ORIGEM_BAIRRO,
                logradouro = TRANSPORTE_ENDERECO_ORIGEM_LOGRADOURO,
                numero = TRANSPORTE_ENDERECO_ORIGEM_NUMERO,
                complemento = TRANSPORTE_ENDERECO_ORIGEM_COMPLEMENTO
            ),
            enderecoDestino = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ENDERECO_DESTINO_CIDADE,
                cep = TRANSPORTE_ENDERECO_DESTINO_CEP,
                bairro = TRANSPORTE_ENDERECO_DESTINO_BAIRRO,
                logradouro = TRANSPORTE_ENDERECO_DESTINO_LOGRADOURO,
                numero = TRANSPORTE_ENDERECO_DESTINO_NUMERO,
                complemento = TRANSPORTE_ENDERECO_DESTINO_COMPLEMENTO
            ),
            tipoTransporte = TipoTransporte.AMBULANCIA
        )

        whenever(transporteApplicationService.cancela(transporteDetailsDTO)).thenThrow(
            TransporteNotFoundException(idTransporte)
        )

        val endpoint = "$BASE_PATH/${idTransporte.id}/credenciamento"
        val requisicao = MockMvcRequestBuilders.delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

}
