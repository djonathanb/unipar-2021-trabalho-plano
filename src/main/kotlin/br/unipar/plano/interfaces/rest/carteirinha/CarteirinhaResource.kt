package br.unipar.plano.interfaces.rest.carteirinha

import br.unipar.plano.domain.carteirinha.model.Carteirinha
import br.unipar.plano.domain.carteirinha.model.IdCarteirinha
import br.unipar.plano.domain.carteirinha.services.CarteirinhaApplicationService
import br.unipar.plano.domain.carteirinha.services.impl.CarteirinhaApplicationServiceImpl
import br.unipar.plano.domain.usuario.IdUsuario
import br.unipar.plano.interfaces.rest.centrais.CentralDTO
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

    @GetMapping
    fun lista(): ResponseEntity<String> {
        return ResponseEntity.ok("Bateu");
    }

    @Operation(summary = "Cria uma nova carteirinha para o usuário e retorna o endereço do novo recurso")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Carteirinha criada com sucesso!")
    ])
    @PostMapping
    fun criarCarteirinha(@RequestBody @Valid idUsuario: IdUsuario): ResponseEntity<Void> {
        val idNovaCarteirinha = carteirinhaApplicationService.criar(idUsuario)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idNovaCarteirinha.idCarteirinha)
                .toUri()

        return ResponseEntity.created(uri).build()
    }

    @PostMapping("/validate")
    fun verificarValidade(@RequestBody @Valid idCarteirinha: IdCarteirinha) : ResponseEntity<Any> {
        try {
            val carteirinha = carteirinhaApplicationService.validaCarteirinha(idCarteirinha)

            return ResponseEntity.ok(carteirinha)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @PostMapping("/register")
    fun registrarEntrega(@RequestBody @Valid idCarteirinha: IdCarteirinha): ResponseEntity<Any> {
        try {
            val carteirinha = carteirinhaApplicationService.registraEntrega(idCarteirinha)
            return ResponseEntity.ok(carteirinha)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    fun registrarExtravio(idUsuario: IdUsuario): Boolean {
        return true;
    }
}