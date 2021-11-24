package br.unipar.plano.interfaces.rest.prenatal

import br.unipar.plano.domain.prenatal.model.IdPreNatal
import br.unipar.plano.domain.prenatal.services.SolicitarPreNatalService
import br.unipar.plano.interfaces.rest.prenatal.PreNatalDTO
import br.unipar.plano.interfaces.rest.prenatal.PreNatalDetailsDTO
import br.unipar.plano.interfaces.rest.prenatal.PreNatalSummaryDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/prenatal")
class PreNatalResource(private val solicitarPreNatalService: SolicitarPreNatalService) {

    @Operation(summary = "Cria um novo pre natal e retorna o id do novo pre natal")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "transporte criado com sucesso")
    ])

    @PostMapping
    fun solicitar(@RequestBody @Valid preNatalDTO: PreNatalDTO): ResponseEntity<Void> {
        val idNovoPreNatal = solicitarPreNatalService.solicitar(preNatalDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovoPreNatal.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @GetMapping
    fun listar(): ResponseEntity<List<PreNatalSummaryDTO>> {
        return ResponseEntity.ok(solicitarPreNatalService.listar())
    }

    @GetMapping("/{idPreNatal}")
    fun buscarPorId(@PathVariable("idPreNatal") idPreNatal: UUID): ResponseEntity<PreNatalDetailsDTO> {
        return ResponseEntity.ok(solicitarPreNatalService.buscarPorId(IdPreNatal(idPreNatal)))
    }

    @GetMapping("/{idPreNatal}")
    fun autorizar(@PathVariable("idPreNatal") idPreNatal: UUID): ResponseEntity<PreNatalDetailsDTO> {
        return ResponseEntity.ok(solicitarPreNatalService.autorizar(IdPreNatal(idPreNatal)))
    }

    @GetMapping("/{idPreNatal}")
    fun cancelar(@PathVariable("idPreNatal") idPreNatal: UUID): ResponseEntity<PreNatalDetailsDTO> {
        return ResponseEntity.ok(solicitarPreNatalService.cancelar(IdPreNatal(idPreNatal)))
    }

}