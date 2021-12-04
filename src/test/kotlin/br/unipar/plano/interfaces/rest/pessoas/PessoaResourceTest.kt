package br.unipar.plano.interfaces.rest.pessoas


import br.unipar.plano.domain.pessoas.model.factories.idPessoa
import br.unipar.plano.domain.pessoas.services.PessoaApplicationService
import br.unipar.plano.domain.pessoas.usecases.impl.PessoaNotFoundException
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

private const val BASE_PATH = "/pessoa"

@WebMvcTest(PessoaResource::class)
class PessoaResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var pessoaApplicationService: PessoaApplicationService


    @Test
    fun `deve retornar 404 ao deletar uma pessoa inexistente`() {
        val idPessoa = idPessoa()

        Mockito.`when`(pessoaApplicationService.deleta(eq(idPessoa))).thenThrow(
            PessoaNotFoundException(idPessoa)
        )

        val endpoint = "${br.unipar.plano.interfaces.rest.pessoas.BASE_PATH}/${idPessoa.id}"
        val requisicao = MockMvcRequestBuilders.delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }


    @Test
    fun `deve retornar 404 ao consultar uma contrato inexistente`() {
        val idPessoa = idPessoa()

        Mockito.`when`(pessoaApplicationService.buscaPorId(eq(idPessoa))).thenThrow(
            PessoaNotFoundException(idPessoa)
        )

        val endpoint = "${br.unipar.plano.interfaces.rest.pessoas.BASE_PATH}/${idPessoa.id}"
        val requisicao = MockMvcRequestBuilders.get(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

}