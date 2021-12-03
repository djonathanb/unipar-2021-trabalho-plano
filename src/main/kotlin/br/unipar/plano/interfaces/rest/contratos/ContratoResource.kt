package br.unipar.plano.interfaces.rest.contratos

import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.contratos.planos.model.Plano
import br.unipar.plano.domain.contratos.services.ContratoApplicationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/contratos")
class ContratoResource(private val contratoApplicationService: ContratoApplicationService) {

    @Operation(summary = "Cria uma novo contrato e retorna o endereço do novo recurso")
    @ApiResponses(ApiResponse(description = "Sucesso ao Criar um contrato", responseCode = "200"),
                  ApiResponse(description = "Caso ocorra algum erro durante a criação do contrato", responseCode = "400")
    )
    @PostMapping
    fun criar(@RequestBody @Valid contratoDTO: ContratoDTO): ResponseEntity<Void> {
        val idNovaCentral = contratoApplicationService.cria(contratoDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovaCentral.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @Operation(summary = "Atualiza uma central a partir do seu id e novo estado")
    @PutMapping("/{idContrato}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ApiResponses(ApiResponse(description = "Sucesso ao atualizar o Contrato", responseCode = "200"),
                  ApiResponse(description = "Caso não encontre algum contrato para atualizar \n " +
                                            "Caso de algum erro durante a atualização do contrato", responseCode = "400"))
    fun atualizar(@PathVariable("idContrato") idContrato: UUID, @RequestBody @Valid contratoDTO: ContratoDTO) {
        contratoApplicationService.atualiza(idContrato = IdContrato(idContrato), contratoDTO = contratoDTO)
    }

    @Operation(summary = "Deleta uma central a partir do seu id")
    @DeleteMapping("/{idContrato}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ApiResponses(ApiResponse(description = "Sucesso ao deletar o contrato", responseCode = "200"),
                  ApiResponse(description = "Caso ocorra algum erro ao deletar o contrato", responseCode = "400"))
    fun deleta(@PathVariable("idContrato") idContrato: UUID) {
        contratoApplicationService.cancelaContrato(idContrato = IdContrato(idContrato))
    }

    @Operation(summary = "Retorna a lista de contratos cadastrados")
    @GetMapping
    @ApiResponses(ApiResponse(description = "Sucesso ao consultar os contrato", responseCode = "200"))
    fun lista(): ResponseEntity<List<ContratoSummaryDTO>> {
        return ResponseEntity.ok(contratoApplicationService.lista())
    }

    @Operation(summary = "Busca os detalhes de um contrato por id")
    @GetMapping("/{idContrato}")
    @ApiResponses(ApiResponse(description = "Sucesso ao consultar o contrato", responseCode = "200"))
    fun buscaPorId(@PathVariable("idContrato") idContrato: UUID ): ResponseEntity<ContratoDetailsDTO> {
        return ResponseEntity.ok(contratoApplicationService.buscaPorId(IdContrato(idContrato)))
    }

    @Operation(summary = "Busca Todos os contratos a partir do Id de um Plano")
    @GetMapping("/buscaporplano/{idPlano}")
    @ApiResponses(ApiResponse(description = "Sucesso ao consultar o contrato por plano", responseCode = "200"))
    fun buscaPorPlano(@PathVariable("idPlano") idPlano: Plano): ResponseEntity<List<ContratoDetailsDTO>> {
        return ResponseEntity.ok(contratoApplicationService.buscaPorPlano(idPlano))
    }

}