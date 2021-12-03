package br.unipar.plano.interfaces.rest.reembolso

import br.unipar.plano.domain.reembolso.services.UsuarioApplicationService
import br.unipar.plano.interfaces.rest.reembolso.dto.ReembolsoDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/solicitacao-reembolso")
class UsuarioResource (private val usuarioApplicationService: UsuarioApplicationService){

    @Operation(summary = "Faz uma solicitação de reembolso e retorna o endereço do novo recurso")
    @PostMapping
    fun solicitaReembolso(@RequestBody @Valid reembolsoDTO: ReembolsoDTO): ResponseEntity<Void> {
        val idNovoReembolso = usuarioApplicationService.solicitaReembolso(reembolsoDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idNovoReembolso.id)
                .toUri()

        return ResponseEntity.created(uri).build()
    }
}
