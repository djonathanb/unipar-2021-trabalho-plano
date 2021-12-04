package br.unipar.plano.interfaces.rest.carteirinha

import br.unipar.plano.domain.carteirinha.services.CarteirinhaApplicationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/carteirinha")
class CarteirinhaResource(private val carteirinhaApplicationService: CarteirinhaApplicationService) {

    @Operation(summary = "Cria uma nova carteirinha para o usuário e retorna o endereço do novo recurso")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Carteirinha criada com sucesso!")
    ])
    @PostMapping
    fun criarCarteirinha(@RequestBody @Valid dto: CarteirinhaDTO): ResponseEntity<Any> {

        try {

            val idNovaCarteirinha = carteirinhaApplicationService.criar(dto)

            val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(idNovaCarteirinha)
                    .toUri()

            return ResponseEntity.created(uri).build()

        } catch (error: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.message)
        }

    }

    @Operation(summary = "Verifica se uma carteirinha é válida ou não")
    @GetMapping("/{numeroCarteirinha}/valida")
    fun verificarValidade(@PathVariable("numeroCarteirinha") numeroCarteirinha: String) : ResponseEntity<Any> {
        try {
            val carteirinha = carteirinhaApplicationService.validaCarteirinha(numeroCarteirinha)

            return ResponseEntity.ok(carteirinha)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @Operation(summary = "Registra a entrega da carteirinha ao usuário")
    @GetMapping("/{numeroCarteirinha}/entrega")
    fun registrarEntrega(@PathVariable("numeroCarteirinha") numeroCarteirinha: String): ResponseEntity<Any> {
        try {
            val carteirinha = carteirinhaApplicationService.registraEntrega(numeroCarteirinha)
            return ResponseEntity.ok(carteirinha)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @Operation(summary = "Registra o extravio e grava o motivo deste")
    @PostMapping("/registermisplacement")
    fun registrarExtravio(@RequestBody @Valid motivoDTO: MotivoDTO): ResponseEntity<Any> {
        try {
            val motivoExtravio = carteirinhaApplicationService.registraExtravio(motivoDTO)
            return ResponseEntity.ok(motivoExtravio)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }
}