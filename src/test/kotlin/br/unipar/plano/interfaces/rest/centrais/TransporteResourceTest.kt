package br.unipar.plano.interfaces.rest.centrais

import br.unipar.plano.domain.carteirinhas.model.Carteirinha
import br.unipar.plano.domain.centrais.model.TipoTransporte
import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.centrais.model.factories.*
import br.unipar.plano.domain.centrais.services.TransporteApplicationService
import br.unipar.plano.domain.centrais.usecases.impl.TransporteNotFoundException
import br.unipar.plano.interfaces.rest.transportes.EnderecoTransporteDTO
import br.unipar.plano.interfaces.rest.transportes.TransporteDTO
import br.unipar.plano.interfaces.rest.transportes.TransporteDetailsDTO
import br.unipar.plano.interfaces.rest.transportes.TransporteResource
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
import java.util.*

private const val BASE_PATH = "/centrais"

@WebMvcTest(TransporteResource::class)
class TransporteResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var transporteApplicationService: TransporteApplicationService

    @Test
    fun `deve retornar 201 e location ao criar uma nova Transporte`() {
        val idNovoTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))
        val localizacaoEsperada = "http://localhost$BASE_PATH/${idNovoTransporte.id}"
        val transporteDTO = TransporteDTO(
            carteirinha = Carteirinha(CARTEIRINHA_TESTE_ID),
            enderecoOrigem = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ORIGEM_ENDERECO_CIDADE,
                cep = TRANSPORTE_ORIGEM_ENDERECO_CEP,
                bairro = TRANSPORTE_ORIGEM_ENDERECO_BAIRRO,
                logradouro = TRANSPORTE_ORIGEM_ENDERECO_LOGRADOURO,
                numero = TRANSPORTE_ORIGEM_ENDERECO_NUMERO,
                complemento = TRANSPORTE_ORIGEM_ENDERECO_COMPLEMENTO
            ),
            enderecoDestino= EnderecoTransporteDTO(
                cidade = TRANSPORTE_DESTINO_ENDERECO_CIDADE,
                cep = TRANSPORTE_DESTINO_ENDERECO_CEP,
                bairro = TRANSPORTE_DESTINO_ENDERECO_BAIRRO,
                logradouro = TRANSPORTE_DESTINO_ENDERECO_LOGRADOURO,
                numero = TRANSPORTE_DESTINO_ENDERECO_NUMERO,
                complemento = TRANSPORTE_DESTINO_ENDERECO_COMPLEMENTO
            ),
            tipoTransporte= TipoTransporte.AMBULANCIA
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
            carteirinha = Carteirinha(CARTEIRINHA_TESTE_ID),
            enderecoOrigem = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ORIGEM_ENDERECO_CIDADE,
                cep = TRANSPORTE_ORIGEM_ENDERECO_CEP,
                bairro = TRANSPORTE_ORIGEM_ENDERECO_BAIRRO,
                logradouro = TRANSPORTE_ORIGEM_ENDERECO_LOGRADOURO,
                numero = TRANSPORTE_ORIGEM_ENDERECO_NUMERO,
                complemento = TRANSPORTE_ORIGEM_ENDERECO_COMPLEMENTO
            ),
            enderecoDestino= EnderecoTransporteDTO(
                cidade = TRANSPORTE_DESTINO_ENDERECO_CIDADE,
                cep = TRANSPORTE_DESTINO_ENDERECO_CEP,
                bairro = TRANSPORTE_DESTINO_ENDERECO_BAIRRO,
                logradouro = TRANSPORTE_DESTINO_ENDERECO_LOGRADOURO,
                numero = TRANSPORTE_DESTINO_ENDERECO_NUMERO,
                complemento = TRANSPORTE_DESTINO_ENDERECO_COMPLEMENTO
            ),
            tipoTransporte= TipoTransporte.AMBULANCIA
        )

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(transporteDTO)

        val requisicao = put(endpoint)
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

            carteirinha = Carteirinha(CARTEIRINHA_TESTE_ID),
            enderecoOrigem = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ORIGEM_ENDERECO_CIDADE,
                cep = TRANSPORTE_ORIGEM_ENDERECO_CEP,
                bairro = TRANSPORTE_ORIGEM_ENDERECO_BAIRRO,
                logradouro = TRANSPORTE_ORIGEM_ENDERECO_LOGRADOURO,
                numero = TRANSPORTE_ORIGEM_ENDERECO_NUMERO,
                complemento = TRANSPORTE_ORIGEM_ENDERECO_COMPLEMENTO
            ),
            enderecoDestino= EnderecoTransporteDTO(
                cidade = TRANSPORTE_DESTINO_ENDERECO_CIDADE,
                cep = TRANSPORTE_DESTINO_ENDERECO_CEP,
                bairro = TRANSPORTE_DESTINO_ENDERECO_BAIRRO,
                logradouro = TRANSPORTE_DESTINO_ENDERECO_LOGRADOURO,
                numero = TRANSPORTE_DESTINO_ENDERECO_NUMERO,
                complemento = TRANSPORTE_DESTINO_ENDERECO_COMPLEMENTO
            ),
            tipoTransporte= TipoTransporte.AMBULANCIA
        )

        `when`(transporteApplicationService.cria(eq(transporteDTO))).thenThrow(
            TransporteNotFoundException(idTransporte)
        )

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(transporteDTO)

        val requisicao = put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }


    @Test
    fun `deve retornar 204 ao deletar uma Transporte`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val requisicao = delete(endpoint)

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
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao consultar uma Transporte`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))
        val transporteDTO = TransporteDetailsDTO(
            id=idTransporte,
            transporteData=TransporteDTO(

                carteirinha = Carteirinha(CARTEIRINHA_TESTE_ID),
                enderecoOrigem = EnderecoTransporteDTO(
                    cidade = TRANSPORTE_ORIGEM_ENDERECO_CIDADE,
                    cep = TRANSPORTE_ORIGEM_ENDERECO_CEP,
                    bairro = TRANSPORTE_ORIGEM_ENDERECO_BAIRRO,
                    logradouro = TRANSPORTE_ORIGEM_ENDERECO_LOGRADOURO,
                    numero = TRANSPORTE_ORIGEM_ENDERECO_NUMERO,
                    complemento = TRANSPORTE_ORIGEM_ENDERECO_COMPLEMENTO
                ),
                enderecoDestino= EnderecoTransporteDTO(
                    cidade = TRANSPORTE_DESTINO_ENDERECO_CIDADE,
                    cep = TRANSPORTE_DESTINO_ENDERECO_CEP,
                    bairro = TRANSPORTE_DESTINO_ENDERECO_BAIRRO,
                    logradouro = TRANSPORTE_DESTINO_ENDERECO_LOGRADOURO,
                    numero = TRANSPORTE_DESTINO_ENDERECO_NUMERO,
                    complemento = TRANSPORTE_DESTINO_ENDERECO_COMPLEMENTO
                ),
                tipoTransporte= TipoTransporte.AMBULANCIA
            )
        )


        whenever(transporteApplicationService.buscaPorId(eq(idTransporte))).thenReturn(transporteDTO)

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = ObjectMapper().writeValueAsString(transporteDTO)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

    @Test
    fun `deve retornar 404 ao consultar uma Transporte inexistente`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))

        `when`(transporteApplicationService.buscaPorId(eq(idTransporte))).thenThrow(
            TransporteNotFoundException(idTransporte)
        )

        val endpoint = "$BASE_PATH/${idTransporte.id}"
        val requisicao = get(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }



    @Test
    fun `deve retornar 404 ao credenciar uma Transporte inexistente`() {
        val idTransporte = IdTransporte(UUID.fromString("1c521a53-38d9-4437-a34c-8da2c7ed0f4c"))
        val transporteDetailsDTO = TransporteDTO(

            carteirinha = Carteirinha(CARTEIRINHA_TESTE_ID),
            enderecoOrigem = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ORIGEM_ENDERECO_CIDADE,
                cep = TRANSPORTE_ORIGEM_ENDERECO_CEP,
                bairro = TRANSPORTE_ORIGEM_ENDERECO_BAIRRO,
                logradouro = TRANSPORTE_ORIGEM_ENDERECO_LOGRADOURO,
                numero = TRANSPORTE_ORIGEM_ENDERECO_NUMERO,
                complemento = TRANSPORTE_ORIGEM_ENDERECO_COMPLEMENTO
            ),
            enderecoDestino= EnderecoTransporteDTO(
                cidade = TRANSPORTE_DESTINO_ENDERECO_CIDADE,
                cep = TRANSPORTE_DESTINO_ENDERECO_CEP,
                bairro = TRANSPORTE_DESTINO_ENDERECO_BAIRRO,
                logradouro = TRANSPORTE_DESTINO_ENDERECO_LOGRADOURO,
                numero = TRANSPORTE_DESTINO_ENDERECO_NUMERO,
                complemento = TRANSPORTE_DESTINO_ENDERECO_COMPLEMENTO
            ),
            tipoTransporte= TipoTransporte.AMBULANCIA)

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

            carteirinha = Carteirinha(CARTEIRINHA_TESTE_ID),
            enderecoOrigem = EnderecoTransporteDTO(
                cidade = TRANSPORTE_ORIGEM_ENDERECO_CIDADE,
                cep = TRANSPORTE_ORIGEM_ENDERECO_CEP,
                bairro = TRANSPORTE_ORIGEM_ENDERECO_BAIRRO,
                logradouro = TRANSPORTE_ORIGEM_ENDERECO_LOGRADOURO,
                numero = TRANSPORTE_ORIGEM_ENDERECO_NUMERO,
                complemento = TRANSPORTE_ORIGEM_ENDERECO_COMPLEMENTO
            ),
            enderecoDestino= EnderecoTransporteDTO(
                cidade = TRANSPORTE_DESTINO_ENDERECO_CIDADE,
                cep = TRANSPORTE_DESTINO_ENDERECO_CEP,
                bairro = TRANSPORTE_DESTINO_ENDERECO_BAIRRO,
                logradouro = TRANSPORTE_DESTINO_ENDERECO_LOGRADOURO,
                numero = TRANSPORTE_DESTINO_ENDERECO_NUMERO,
                complemento = TRANSPORTE_DESTINO_ENDERECO_COMPLEMENTO
            ),
            tipoTransporte= TipoTransporte.AMBULANCIA)

        whenever(transporteApplicationService.cancela(transporteDetailsDTO)).thenThrow(
            TransporteNotFoundException(idTransporte)
        )

        val endpoint = "$BASE_PATH/${idTransporte.id}/credenciamento"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

}