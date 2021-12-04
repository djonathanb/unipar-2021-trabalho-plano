package br.unipar.plano.interfaces.rest.contratos


import br.unipar.plano.domain.contratos.model.factories.idContrato
import br.unipar.plano.domain.contratos.services.ContratoApplicationService
import br.unipar.plano.domain.contratos.usecases.impl.ContratoNotFoundException
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


private const val BASE_PATH = "/contratos"

@WebMvcTest(ContratoResource::class)
class ContratoResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var contratoApplicationService: ContratoApplicationService

    @Test
    fun `deve retornar 204 ao cancelar um contrato`() {
        val idContrato = idContrato()

        val endpoint = "${br.unipar.plano.interfaces.rest.contratos.BASE_PATH}/${idContrato.id}"
        val requisicao = MockMvcRequestBuilders.delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        verify(contratoApplicationService).cancelaContrato(eq(idContrato))
    }

    @Test
    fun `deve retornar 404 ao cancelar uma central inexistente`() {
        val idContrato = idContrato()

        Mockito.`when`(contratoApplicationService.cancelaContrato(eq(idContrato))).thenThrow(
            ContratoNotFoundException(idContrato)
        )

        val endpoint = "${br.unipar.plano.interfaces.rest.contratos.BASE_PATH}/${idContrato.id}"
        val requisicao = MockMvcRequestBuilders.delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }


    @Test
    fun `deve retornar 404 ao consultar uma contrato inexistente`() {
        val idContrato = idContrato()

        Mockito.`when`(contratoApplicationService.buscaPorId(eq(idContrato))).thenThrow(
            ContratoNotFoundException(idContrato)
        )

        val endpoint = "${br.unipar.plano.interfaces.rest.contratos.BASE_PATH}/${idContrato.id}"
        val requisicao = MockMvcRequestBuilders.get(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    
}