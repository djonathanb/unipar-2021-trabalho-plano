package br.unipar.plano.interfaces.rest.dependentes

import br.unipar.plano.domain.dependentes.model.factories.idDependente
import br.unipar.plano.domain.dependentes.services.DependenteApplicationService
import br.unipar.plano.domain.dependentes.usecase.impl.DependenteNotFoundException
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

private const val BASE_PATH = "/dependente"

@WebMvcTest(DependenteResource::class)
class DependenteResourceTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var dependenteApplicationService: DependenteApplicationService

    @Test
    fun `deve retornar 204 ao cancelar um dependente`() {
        val idDependente = idDependente()

        val endpoint = "${br.unipar.plano.interfaces.rest.dependentes.BASE_PATH}/${idDependente.id}"
        val requisicao = MockMvcRequestBuilders.delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        verify(dependenteApplicationService).deleta(eq(idDependente))
    }

    @Test
    fun `deve retornar 404 ao cancelar um dependente inexistente`() {
        val idDependente = idDependente()

        Mockito.`when`(dependenteApplicationService.deleta(eq(idDependente))).thenThrow(
            DependenteNotFoundException(idDependente)
        )

        val endpoint = "${br.unipar.plano.interfaces.rest.dependentes.BASE_PATH}/${idDependente.id}"
        val requisicao = MockMvcRequestBuilders.delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }


    @Test
    fun `deve retornar 404 ao consultar uma dependente inexistente`() {
        val idDependente = idDependente()

        Mockito.`when`(dependenteApplicationService.buscaPorId(eq(idDependente))).thenThrow(
            DependenteNotFoundException(idDependente)
        )

        val endpoint = "${br.unipar.plano.interfaces.rest.dependentes.BASE_PATH}/${idDependente.id}"
        val requisicao = MockMvcRequestBuilders.get(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}