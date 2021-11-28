package br.unipar.plano.interfaces.rest.transportes

import br.unipar.plano.domain.centrais.model.IdTransporte
import br.unipar.plano.domain.centrais.services.TransporteApplicationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/transportes")
class TransporteResource(private val transporteApplicationService: TransporteApplicationService) {

    @Operation(summary = "Cria um novo transporte e retorna o endereço do novo recurso")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "transporte criado com sucesso")
    ])
    @PostMapping
    fun criar(@RequestBody @Valid transporteDTO: TransporteDTO): ResponseEntity<Void> {
        val idNovaTransporte = transporteApplicationService.cria(transporteDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovaTransporte.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @PostMapping("/cancela")
    fun cancelar(@RequestBody @Valid transporteDTO: TransporteDTO): ResponseEntity<Void> {
        val idNovaTransporte = transporteApplicationService.cancela(transporteDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovaTransporte.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @PostMapping("/aprova")
    fun aprova(@RequestBody @Valid transporteDTO: TransporteDTO): ResponseEntity<Void> {
        val idNovaTransporte = transporteApplicationService.aprova(transporteDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovaTransporte.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }


    @GetMapping
    fun lista(): ResponseEntity<List<TransporteSummaryDTO>> {
        return ResponseEntity.ok(transporteApplicationService.lista())
    }

    @GetMapping("/{idTransporte}")
    fun buscaPorId(@PathVariable("idTransporte") idTransporte: UUID): ResponseEntity<TransporteDetailsDTO> {
        return ResponseEntity.ok(transporteApplicationService.buscaPorId(IdTransporte(idTransporte)))
    }

}
