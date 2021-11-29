package br.unipar.plano.interfaces.rest.pessoas



import br.unipar.plano.domain.contratos.model.IdContrato
import br.unipar.plano.domain.pessoas.model.IdPessoa
import br.unipar.plano.domain.pessoas.services.PessoaApplicationService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/pessoas")
class PessoaResource(private val pessoaApplicationService: PessoaApplicationService) {

    @Operation(summary = "Cadastra uma nova pessoa")
    @PostMapping
    fun criar(@RequestBody @Valid pessoaDTO: PessoaDTO): ResponseEntity<Void> {
        val idNovaCentral = pessoaApplicationService.cria(pessoaDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idNovaCentral.id)
            .toUri()

        return ResponseEntity.created(uri).build()
    }

    @Operation(summary = "Atualiza uma pessoa a partir do seu id")
    @PutMapping("/{idPessoa}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun atualizar(@PathVariable("idPessoa") idPessoa: IdPessoa, @RequestBody @Valid pessoaDTO: PessoaDTO) {
        pessoaApplicationService.atualiza(idPessoa = idPessoa, pessoaDTO = pessoaDTO)
    }

    @Operation(summary = "Deleta uma pessoa a partir do seu id")
    @DeleteMapping("/{idPessoa}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun deleta(@PathVariable("idPessoa") idPessoa: IdPessoa) {
        pessoaApplicationService.deleta(idPessoa = idPessoa)
    }


    @Operation(summary = "Retorna a lista de contratos cadastrados")
    @GetMapping
    fun lista(): ResponseEntity<List<PessoaDTO>> {
        return ResponseEntity.ok(pessoaApplicationService.lista())
    }

    @Operation(summary = "Busca os detalhes de um contrato por id")
    @GetMapping("/{idPessoa}")
    fun buscaPorId(@PathVariable("idPessoa") idPessoa: UUID ): ResponseEntity<PessoaDetailsDTO> {
        return ResponseEntity.ok(pessoaApplicationService.buscaPorId(IdPessoa(idPessoa)))
    }


}