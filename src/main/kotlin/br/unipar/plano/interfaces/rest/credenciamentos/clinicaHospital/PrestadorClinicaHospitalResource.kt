package br.unipar.plano.interfaces.rest.credenciamentos.clinicaHospital

import br.unipar.plano.domain.credenciamentos.model.clinicaHospital.IdPrestadorClinicaHospital
import br.unipar.plano.domain.credenciamentos.model.prestadorMedico.IdPrestadorMedico
import br.unipar.plano.domain.credenciamentos.services.clinicaHospital.PrestClinHospAppService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/prestadorClinicaHospital")
class PrestadorClinicaHospitalResource(private val prestClinHospAppService: PrestClinHospAppService) {

    @Operation(summary = "Credencia clinica/hospital")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Clinica/Hospital criada com sucesso")
    ])
    @PostMapping
    fun criar(@RequestBody @Valid prestClinHospDTO: PrestClinHospDTO): ResponseEntity<Void> {
        val idNovaClinicaHospital = prestClinHospAppService.cria(prestClinHospDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovaClinicaHospital.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @GetMapping
    fun lista(): ResponseEntity<List<PrestClinHospSummaryDTO>> {
        return ResponseEntity.ok(prestClinHospAppService.lista())
    }

    @GetMapping("/{idPrestadorClinicaHospital}")
    fun buscaPorId(@PathVariable("idPrestadorClinicaHospital") idPrestadorClinicaHospital: UUID): ResponseEntity<PrestClinHospDetailsDTO> {
        return ResponseEntity.ok(prestClinHospAppService.buscaPorId(IdPrestadorClinicaHospital(idPrestadorClinicaHospital)))
    }

    @PostMapping("/{idPrestadorClinicaHospital}/credenciamento")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun credenciar(@PathVariable("idPrestadorClinicaHospital") idPrestadorClinicaHospital: UUID) {
        prestClinHospAppService.credenciar(IdPrestadorClinicaHospital(idPrestadorClinicaHospital))
    }

    @DeleteMapping("/{idPrestadorClinicaHospital}/descredenciamento")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun descredenciar(@PathVariable("idPrestadorClinicaHospital") idPrestadorClinicaHospital: UUID) {
        prestClinHospAppService.descredenciar(IdPrestadorClinicaHospital(idPrestadorClinicaHospital))
    }

}

