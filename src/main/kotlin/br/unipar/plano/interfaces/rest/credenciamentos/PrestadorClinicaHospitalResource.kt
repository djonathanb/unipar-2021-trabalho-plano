package br.unipar.plano.interfaces.rest.credenciamentos

import br.unipar.plano.domain.credenciamentos.model.IdPrestadorClinicaHospital
//import br.unipar.plano.domain.credenciamentos.services.PrestClinHospAppService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

class PrestadorClinicaHospitalResource {
/*
    @RestController
    @RequestMapping("/centrais")
    class CentralResource(private val prestClinHospAppService: PrestClinHospAppService) {

        @Operation(summary = "Cria uma nova central e retorna o endereço do novo recurso")
        @ApiResponses(value = [
            ApiResponse(responseCode = "201", description = "central criada com sucesso")
        ])
        @PostMapping
        fun criar(@RequestBody @Valid prestClinHospDTO: PrestClinHospDTO): ResponseEntity<Void> {
            val idNovaCentral = prestClinHospAppService.cria(prestClinHospDTO)

            val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idNovaCentral.id)
                .toUri()

            return ResponseEntity.created(uri).build()
        }

        @GetMapping
        fun lista(): ResponseEntity<List<PrestClinHospDTO>> {
            return ResponseEntity.ok(prestClinHospAppService.lista())
        }

        @GetMapping("/{idCentral}")
        fun buscaPorId(@PathVariable("idPrestadorClinicaHospital") idPrestadorClinicaHospital: UUID): ResponseEntity<PrestClinHospDTO> {
            return ResponseEntity.ok(prestClinHospAppService.buscaPorId(IdPrestadorClinicaHospital(idPrestadorClinicaHospital)))
        }

    }

 */

}