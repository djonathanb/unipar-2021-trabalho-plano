package br.unipar.plano.interfaces.rest.credenciamentos

import br.unipar.plano.domain.credenciamentos.model.IdEspecialidade
import br.unipar.plano.domain.credenciamentos.services.EspecialidadeAppService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/especialidade")
class EspecialidadeResource (private val especialidadeAppService: EspecialidadeAppService) {

        @Operation(summary = "Cria uma especialidade")
        @ApiResponses(value = [
            ApiResponse(responseCode = "201", description = "Especialidade criada com sucesso")
        ])
        @PostMapping
        fun criar(@RequestBody @Valid espDTO: EspDTO): ResponseEntity<Void> {
            val idNovaEspecialidade  = especialidadeAppService.cria(espDTO)

            val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idNovaEspecialidade.id)
                .toUri()

            return ResponseEntity.created(uri).build()
        }

        @GetMapping
        fun lista(): ResponseEntity<List<EspecialidadeSummaryDTO>> {
            return ResponseEntity.ok(especialidadeAppService.lista())
        }

        @GetMapping("/{idEspecialidade}")
        fun buscaPorId(@PathVariable("idEspecialidade") idEspecialidadeDTO: UUID): ResponseEntity<EspecialidadeDetailsDTO> {
            return ResponseEntity.ok(especialidadeAppService.buscaPorId(IdEspecialidade(idEspecialidadeDTO)))
        }

    }
