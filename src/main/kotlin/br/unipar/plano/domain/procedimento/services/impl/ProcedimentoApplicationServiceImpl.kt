package br.unipar.plano.domain.procedimento.services.impl

import br.unipar.plano.domain.procedimento.model.IdProcedimento
import br.unipar.plano.domain.procedimento.services.ProcedimentoApplicationService
import br.unipar.plano.domain.procedimento.services.ProcedimentoQueryService
import br.unipar.plano.domain.procedimento.usecases.AtualizaProcedimentoUseCase
import br.unipar.plano.domain.procedimento.usecases.CriaProcedimentoUseCase
import br.unipar.plano.domain.procedimento.usecases.CancelaProcedimentoUseCase
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoDTO
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoDetailsDTO
import br.unipar.plano.interfaces.rest.procedimento.ProcedimentoSummaryDTO
import org.springframework.stereotype.Service

@Service
class ProcedimentoApplicationServiceImpl(
    private val procedimentoQueryService: ProcedimentoQueryService,
    private val criaProcedimentoUseCase: CriaProcedimentoUseCase,
    private val atualizaProcedimentoUseCase: AtualizaProcedimentoUseCase,
    private val cancelaProcedimentoUseCase: CancelaProcedimentoUseCase
) : ProcedimentoApplicationService {

    override fun cria(procedimentoDTO: ProcedimentoDTO): IdProcedimento {
        val procedimento = procedimentoDTO.toModel(IdProcedimento())
        val novoProcedimento = criaProcedimentoUseCase.executa(procedimento)
        return novoProcedimento.id
    }

    override fun atualiza(idProcedimento: IdProcedimento, procedimentoDTO: ProcedimentoDTO) {
        atualizaProcedimentoUseCase.executa(idProcedimento) {
            it.with(
                dataProcedimento = procedimentoDTO.dataProcedimento
            )
        }
    }

    override fun cancela(idProcedimento: IdProcedimento) {
        cancelaProcedimentoUseCase.executa(idProcedimento)
    }

    override fun lista() = procedimentoQueryService.lista().map(ProcedimentoSummaryDTO::toDTO)

    override fun buscaPorId(idProcedimento: IdProcedimento): ProcedimentoDetailsDTO {
        val procedimento = procedimentoQueryService.buscaPorId(idProcedimento)
        return ProcedimentoDetailsDTO.toDTO(procedimento)
    }
}