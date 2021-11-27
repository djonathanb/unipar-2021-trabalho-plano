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
    fun criarCarteirinha(@RequestBody @Valid dto: CarteirinhaDTO): ResponseEntity<Void> {

        val idNovaCarteirinha = carteirinhaApplicationService.criar(dto)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idNovaCarteirinha)
                .toUri()

        return ResponseEntity.created(uri).build()
    }

    @PostMapping("/validate")
    fun verificarValidade(@RequestBody @Valid dto: CarteirinhaDTO) : ResponseEntity<Any> {
        try {
            val carteirinha = carteirinhaApplicationService.validaCarteirinha(dto)

            return ResponseEntity.ok(carteirinha)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @PostMapping("/register")
    fun registrarEntrega(@RequestBody @Valid dto: CarteirinhaDTO): ResponseEntity<Any> {
        try {
            val carteirinha = carteirinhaApplicationService.registraEntrega(dto)
            return ResponseEntity.ok(carteirinha)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    fun registrarExtravio(idUsuario: Int): Boolean {
        return true
    }
}