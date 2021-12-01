package br.unipar.plano.interfaces.rest.credenciamentos.prestadorMedico

@WebMvcTest(PrestadorMedicoResource::class)
class PrestadorMedicoResourceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var prestadormedicoApplicationService: PrestadorMedicoApplicationService

    @Test
    fun `deve retornar 201 e location ao criar uma nova prestadormedico`() {
        val idNovaPrestadorMedico = idPrestadorMedico()
        val localizacaoEsperada = "http://localhost$BASE_PATH/${idNovaPrestadorMedico.id}"
        val prestadormedicoDTO = prestadormedicoDTO()

        `when`(prestadormedicoApplicationService.cria(any())).thenReturn(idNovaPrestadorMedico)

        val endpoint = BASE_PATH
        val conteudoJson = ObjectMapper().writeValueAsString(prestadormedicoDTO)

        val requisicao = post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isCreated)
            .andExpect(header().string("location", localizacaoEsperada))

        verify(prestadormedicoApplicationService).cria(eq(prestadormedicoDTO))
    }

    @Test
    fun `deve retornar 400 ao criar uma prestadormedico se nome em branco`() {
        val prestadormedicoDTO = prestadormedicoDTO(
            nome = ""
        )

        val endpoint = BASE_PATH
        val conteudoJson = ObjectMapper().writeValueAsString(prestadormedicoDTO)

        val requisicao = post(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isBadRequest)

        verify(prestadormedicoApplicationService, never()).cria(any())
    }

    @Test
    fun `deve retornar 204 ao atualizar uma prestadormedico`() {
        val idPrestadorMedico = idPrestadorMedico()
        val prestadormedicoDTO = prestadormedicoDTO()

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(prestadormedicoDTO)

        val requisicao = put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(prestadormedicoApplicationService).atualiza(eq(idPrestadorMedico), eq(prestadormedicoDTO))
    }

    @Test
    fun `deve retornar 404 ao atualizar uma prestadormedico inexistente`() {
        val idPrestadorMedico = idPrestadorMedico()
        val prestadormedicoDTO = prestadormedicoDTO()

        `when`(prestadormedicoApplicationService.atualiza(eq(idPrestadorMedico), eq(prestadormedicoDTO))).thenThrow(
            PrestadorMedicoNotFoundException(idPrestadorMedico)
        )

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(prestadormedicoDTO)

        val requisicao = put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 400 ao atualizar uma prestadormedico se nome em branco`() {
        val idPrestadorMedico = idPrestadorMedico()
        val prestadormedicoDTO = prestadormedicoDTO(nome = "")

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val conteudoJson = ObjectMapper().writeValueAsString(prestadormedicoDTO)

        val requisicao = put(endpoint)
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson)

        mockMvc.perform(requisicao)
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `deve retornar 204 ao deletar uma prestadormedico`() {
        val idPrestadorMedico = idPrestadorMedico()

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(prestadormedicoApplicationService).deleta(eq(idPrestadorMedico))
    }

    @Test
    fun `deve retornar 404 ao deletar uma prestadormedico inexistente`() {
        val idPrestadorMedico = idPrestadorMedico()

        `when`(prestadormedicoApplicationService.deleta(eq(idPrestadorMedico))).thenThrow(
            PrestadorMedicoNotFoundException(idPrestadorMedico)
        )

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao consultar uma prestadormedico`() {
        val idPrestadorMedico = idPrestadorMedico()
        val prestadormedicoDetailsDTO = prestadormedicoDetailsDTO()

        whenever(prestadormedicoApplicationService.buscaPorId(eq(idPrestadorMedico))).thenReturn(prestadormedicoDetailsDTO)

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = ObjectMapper().writeValueAsString(prestadormedicoDetailsDTO)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

    @Test
    fun `deve retornar 404 ao consultar uma prestadormedico inexistente`() {
        val idPrestadorMedico = idPrestadorMedico()

        `when`(prestadormedicoApplicationService.buscaPorId(eq(idPrestadorMedico))).thenThrow(
            PrestadorMedicoNotFoundException(idPrestadorMedico)
        )

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}"
        val requisicao = get(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 200 e corpo ao listar as centrais`() {
        val listaCentrais = listOf(
            prestadormedicoSummaryDTO(),
            prestadormedicoSummaryDTO(staticId = false),
        )

        whenever(prestadormedicoApplicationService.lista()).thenReturn(listaCentrais)

        val endpoint = BASE_PATH
        val requisicao = get(endpoint)

        val mvcResult = mockMvc.perform(requisicao)
            .andExpect(status().isOk)
            .andReturn()

        val resultadoEsperado = ObjectMapper().writeValueAsString(listaCentrais)
        assertEquals(resultadoEsperado, mvcResult.response.contentAsString)
    }

    @Test
    fun `deve retornar 204 ao credenciar uma prestadormedico`() {
        val idPrestadorMedico = idPrestadorMedico()

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}/credenciamento"
        val requisicao = post(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(prestadormedicoApplicationService).credenciar(eq(idPrestadorMedico))
    }

    @Test
    fun `deve retornar 404 ao credenciar uma prestadormedico inexistente`() {
        val idPrestadorMedico = idPrestadorMedico()

        whenever(prestadormedicoApplicationService.credenciar(eq(idPrestadorMedico))).thenThrow(
            PrestadorMedicoNotFoundException(idPrestadorMedico)
        )

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}/credenciamento"
        val requisicao = post(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deve retornar 204 ao descredenciar uma prestadormedico`() {
        val idPrestadorMedico = idPrestadorMedico()

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}/credenciamento"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNoContent)

        verify(prestadormedicoApplicationService).descredenciar(eq(idPrestadorMedico))
    }

    @Test
    fun `deve retornar 404 ao descredenciar uma prestadormedico inexistente`() {
        val idPrestadorMedico = idPrestadorMedico()

        whenever(prestadormedicoApplicationService.descredenciar(eq(idPrestadorMedico))).thenThrow(
            PrestadorMedicoNotFoundException(idPrestadorMedico)
        )

        val endpoint = "$BASE_PATH/${idPrestadorMedico.id}/credenciamento"
        val requisicao = delete(endpoint)

        mockMvc.perform(requisicao)
            .andExpect(status().isNotFound)
    }

}