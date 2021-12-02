package br.unipar.plano.interfaces.rest.planos

import br.unipar.plano.domain.centrais.model.IdCentral
import br.unipar.plano.domain.planos.model.IdPlano
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid
import br.unipar.plano.domain.planos.services.PlanoApplicationService
import br.unipar.plano.interfaces.rest.centrais.CentralDetailsDTO
import br.unipar.plano.interfaces.rest.centrais.CentralSummaryDTO

@RestController
@RequestMapping("/planos")
class PlanoResource(private val planoApplicationService: PlanoApplicationService) {

    @Operation(summary = "Cria um novo plano e retorna o endereço do novo recurso")
    @PostMapping
    fun criar(@RequestBody @Valid planoDTO: PlanoDTO): ResponseEntity<Void> {
        val idNovoPlano = planoApplicationService.cria(planoDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovoPlano.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @Operation(summary = "Deleta um plano a partir do seu id")
    @DeleteMapping("/{idPlano}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun deleta(@PathVariable("idPlano") idPlano: UUID) {
        planoApplicationService.deleta(idPlano = IdPlano(idPlano))
    }

    @Operation(summary = "Retorna a lista de planos cadastrados")
    @GetMapping
    fun lista(): ResponseEntity<List<PlanoSummaryDTO>> {
        return ResponseEntity.ok(planoApplicationService.lista())
    }

    @Operation(summary = "Busca os detalhes de um plano por id")
    @GetMapping("/{idPlano}")
    fun buscaPorId(@PathVariable("idPlano") idPlano: UUID): ResponseEntity<PlanoDetailsDTO> {
        return ResponseEntity.ok(planoApplicationService.buscaPorId(IdPlano(idPlano)))
    }


}
