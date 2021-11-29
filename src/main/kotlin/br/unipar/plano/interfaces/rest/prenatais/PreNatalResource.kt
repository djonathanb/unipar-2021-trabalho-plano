package br.unipar.plano.interfaces.rest.prenatais

import br.unipar.plano.domain.prenatal.model.IdPreNatal
import br.unipar.plano.domain.prenatal.services.SolicitarPreNatalService
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

    @Operation(summary = "Retorna uma lista completa com todos os pre-natais")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Pre natais listados")
    ])
    @GetMapping
    fun listar(): ResponseEntity<List<PreNatalSummaryDTO>> {
        return ResponseEntity.ok(solicitarPreNatalService.listar())
    }

    @Operation(summary = "Busca informações de um pre-natal através do ID passado no URL")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Pre-natal encontrado"),
        ApiResponse(responseCode = "404", description = "Pre-natal não encontrado")
    ])
    @GetMapping("/{idPreNatal}")
    fun buscarPorId(@PathVariable("idPreNatal") idPreNatal: UUID): ResponseEntity<PreNatalDetailsDTO> {
        return ResponseEntity.ok(solicitarPreNatalService.buscarPorId(IdPreNatal(idPreNatal)))
    }

    @Operation(summary = "Autoriza um pre-natal através do ID passado no URL")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Pre-natal encontrado"),
        ApiResponse(responseCode = "404", description = "Pre-natal não encontrado")
    ])
    @GetMapping("/{idPreNatal}")
    fun autorizar(@PathVariable("idPreNatal") idPreNatal: UUID): ResponseEntity<PreNatalDetailsDTO> {
        return ResponseEntity.ok(solicitarPreNatalService.autorizar(IdPreNatal(idPreNatal)))
    }

    @Operation(summary = "Cancela um pre-natal através do ID passado no URL")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Pre-natal encontrado"),
        ApiResponse(responseCode = "404", description = "Pre-natal não encontrado")
    ])
    @GetMapping("/{idPreNatal}")
    fun cancelar(@PathVariable("idPreNatal") idPreNatal: UUID): ResponseEntity<PreNatalDetailsDTO> {
        return ResponseEntity.ok(solicitarPreNatalService.cancelar(IdPreNatal(idPreNatal)))
    }

}