package br.unipar.plano.interfaces.rest.solicitacaoprocedimento

import br.unipar.plano.domain.solicitacaoprocedimento.model.IdSolicitacaoProcedimento
import br.unipar.plano.domain.solicitacaoprocedimento.service.SolicitacaoProcedimentoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/solicitacao-liberacao-procedimento")
class SolicitacaoProcedimentoResource(val solicitacaoProcedimentoService: SolicitacaoProcedimentoService) {

    @PostMapping("/criar")
    @ResponseStatus(code = HttpStatus.CREATED)
    fun insert(@RequestBody @Valid solicitacaoProcedimentoDTO: SolicitacaoProcedimentoDTO): ResponseEntity<Void> {
        val idSolicitacaoProcedimento = solicitacaoProcedimentoService.insert(solicitacaoProcedimentoDTO)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idSolicitacaoProcedimento.id)
            .toUri()

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/liberar/{idSolicitacaoProcedimento}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun liberarSolicitacao(@PathVariable idSolicitacaoProcedimento: IdSolicitacaoProcedimento) {
        solicitacaoProcedimentoService.liberarSolicitacao(idSolicitacaoProcedimento);
    }

    @PutMapping("/rejeitar/{idSolicitacaoProcedimento}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun rejeitarSolicitacao(@PathVariable solicitacaoId: IdSolicitacaoProcedimento, @RequestParam descricaoRejeicao: String) {
        solicitacaoProcedimentoService.rejeitarSolicitacao(solicitacaoId, descricaoRejeicao);
    }

    @GetMapping
    fun lista(): ResponseEntity<List<SolicitacaoProcedimentoSummaryDTO>> {
        return ResponseEntity.ok(solicitacaoProcedimentoService.lista())
    }

    @GetMapping("/{idSolicitacaoProcedimento}")
    fun buscaPorId(@PathVariable("idSolicitacaoProcedimento") idSolicitacaoProcedimento: UUID): ResponseEntity<SolicitacaoProcedimentoDetailsDTO> {
        return ResponseEntity.ok(solicitacaoProcedimentoService.buscaPorId(IdSolicitacaoProcedimento(idSolicitacaoProcedimento)))
    }

    @DeleteMapping("/{idSolicitacaoProcedimento}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    fun descredenciar(@PathVariable("idSolicitacaoProcedimento") idSolicitacaoProcedimento: UUID) {
        solicitacaoProcedimentoService.deleta(IdSolicitacaoProcedimento(idSolicitacaoProcedimento))
    }
}