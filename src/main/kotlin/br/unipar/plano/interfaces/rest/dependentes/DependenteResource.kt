package br.unipar.plano.interfaces.rest.dependentes


import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.dependentes.model.IdDependente
import br.unipar.plano.domain.dependentes.services.DependenteApplicationService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/dependente")
class DependenteResource(private val dependenteApplicationService: DependenteApplicationService) {

    @Operation(summary = "Cria novo dependente")
    @PostMapping
    fun criar(@RequestBody @Valid dependenteDTO: DependenteDTO):  ResponseEntity<Void> {
        val idNovoDependente = dependenteApplicationService.cria(dependenteDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovoDependente.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @Operation(summary = "Deleta uma dependente a partir do seu id")
    @DeleteMapping("/{idDependente}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun deleta(@PathVariable("idDependente") idDependente : UUID) {
        dependenteApplicationService.deleta(idDependente = IdDependente(idDependente))
    }


    @Operation(summary = "Busca os detalhes de uma dependente por id")
    @GetMapping("/{idDependente}")
    fun buscaPorId(@PathVariable("idDependente") idDependente: UUID): ResponseEntity<DependenteDetailsDTO> {
        return ResponseEntity.ok(dependenteApplicationService.buscaPorId(IdDependente(idDependente)))
    }

    @Operation(summary = "Busca os dependente pelo ID do contrato")
    @GetMapping("/busacaporcontrato/{idContrato}")
    fun buscaPorContrato(@PathVariable("idContrato") idContrato : UUID): ResponseEntity<List<DependenteDetailsDTO>>{
        return ResponseEntity.ok(dependenteApplicationService.buscaPorContrato(IdContrato(idContrato)))
    }

}