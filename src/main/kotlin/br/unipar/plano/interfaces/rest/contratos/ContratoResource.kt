package br.unipar.plano.interfaces.rest.contratos



import br.unipar.plano.domain.contratos.model.IdContrato
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
    @ApiResponses(ApiResponse(description = "", responseCode = "200"))// Para documentação do Swagger
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
    fun atualizar(@PathVariable("idContrato") idContrato: UUID, @RequestBody @Valid contratoDTO: ContratoDTO) {
        contratoApplicationService.atualiza(idContrato = IdContrato(idContrato), contratoDTO = contratoDTO)
    }

    @Operation(summary = "Deleta uma central a partir do seu id")
    @DeleteMapping("/{idContrato}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun deleta(@PathVariable("idContrato") idContrato: UUID) {
        contratoApplicationService.deleta(idContrato = IdContrato(idContrato))
    }

    /*@PostMapping("/{idCentral}/credenciamento")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun credenciar(@PathVariable("idCentral") idCentral: UUID) {
        contratoApplicationService.credenciar(IdCentral(idCentral))
    }*/

    /*@DeleteMapping("/{idCentral}/credenciamento")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun descredenciar(@PathVariable("idCentral") idCentral: UUID) {
        contratoApplicationService.descredenciar(IdCentral(idCentral))
    }*/

    @Operation(summary = "Retorna a lista de contratos cadastrados")
    @GetMapping
    fun lista(): ResponseEntity<List<ContratoSummaryDTO>> {
        return ResponseEntity.ok(contratoApplicationService.lista())
    }

    @Operation(summary = "Busca os detalhes de um contrato por id")
    @GetMapping("/{idContrato}")
    fun buscaPorId(@PathVariable("idContrato") idContrato: UUID ): ResponseEntity<ContratoDetailsDTO> {
        return ResponseEntity.ok(contratoApplicationService.buscaPorId(IdContrato(idContrato)))
    }

    @Operation(summary = "Busca Todos os contratos a partir do Id de um Plano")
    @GetMapping("/buscaplano/{idPlano}")
    fun buscaPorPlano(@PathVariable("idPlano") idPlano: UUID): ResponseEntity<List<ContratoDetailsDTO>> {
        return ResponseEntity.ok(contratoApplicationService.buscaPorPlano(idPlano))
    }

}